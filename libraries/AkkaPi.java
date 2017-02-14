import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.routing.RoundRobinPool;
import scala.concurrent.duration.Duration;
import java.util.concurrent.TimeUnit;

// Program Design
//   Master actor initiates computation and creates a set of Worker actors
//   Master actor splits up work into discreate tasks and sends these tasks to different works (round-robin)
//   Master actor waits until all worker actors complete their task and sent back results for aggregation
//   Master actor sends the aggregated result to Listener for printing out
// 
// Listener
//   |
// Master actor <-> Worker actor
//              <-> Worker actor
//              <-> Worker actor

public class AkkaPi {
 
    // 0) Bootstrap the calculation
    public static void main(String[] args) {
        AkkaPi pi = new AkkaPi();              // create a new instance of AkkaPi
        pi.calculate(4, 10000, 10000);         // call calculate() method
    }

    // calculate(): start up Master actor and wait for it to finish
    public void calculate(final int nrOfWorkers, final int nrOfElements, final int nrOfMessages) {
        // 0.1) Create an Akka system
        ActorSystem system = ActorSystem.create("PiSystem");
        // 0.2) create the result listener, which will print the result and shutdown the system
        final ActorRef listener = system.actorOf(Props.create(Listener.class), "listener");
        // 0.3) create the master
        ActorRef master = system.actorOf(
            Props.create(
                Master.class,
                nrOfWorkers, nrOfMessages, nrOfElements, listener
            ),
            "master"
        );
 
        // start the calculation
        master.tell(new Calculate(), ActorRef.noSender());
    }

    // 1) Creating the messages
    //
    // 1.1) Calculate: sent to Master actor to start the calculation
    static class Calculate {
    }

    // 1.2) Work: sent from Master actor to Worker actors containing the work assignment 
    static class Work {
        private final int start;
        private final int nrOfElements;
 
        public Work(int start, int nrOfElements) {
            this.start = start;
            this.nrOfElements = nrOfElements;
        }
 
        public int getStart() {
            return start;
        }
 
        public int getNrOfElements() {
            return nrOfElements;
        }
    }

    // 1.3) Result: sent from the Worker actors to Master actor containing result from worker’s calculation 
    static class Result {
        private final double value;
 
        public Result(double value) {
            this.value = value;
        }
 
        public double getValue() {
            return value;
        }
    }

    // 1.4) PiApproximation: sent from Master actor to Listener actor containing final result and calculation time
    static class PiApproximation {
        private final double pi;
        private final Duration duration;
 
        public PiApproximation(double pi, Duration duration) {
            this.pi = pi;
            this.duration = duration;
        }
 
        public double getPi() {
            return pi;
        }
 
        public Duration getDuration() {
            return duration;
        }
    }

    // 2) Creating the worker
    public static class Worker extends UntypedActor {
 
        private double calculatePiFor(int start, int nrOfElements) {
            double acc = 0.0;
            for (int i = start * nrOfElements; i <= ((start + 1) * nrOfElements - 1); i++) {
                acc += 4.0 * (1 - (i % 2) * 2) / (2 * i + 1);
            }
            return acc;
        }
 
        // onReceive(): define the message handler
        //              ex. this actor should be able to handle the Work message
        public void onReceive(Object message) {
            if (message instanceof Work) {
                Work work = (Work) message;
                // invoke the calculatePiFor() method for the assigned calculation task
                double result = calculatePiFor(work.getStart(), work.getNrOfElements());
                // send the Result message and back to the original sender, i.e. Master actor 
                getSender().tell(new Result(result), getSelf());
            } else {
                unhandled(message);
            }
        }
    }

    // 3) Creating the master
    public static class Master extends UntypedActor {
        private final int nrOfMessages; // the number of tasks sent out
        private final int nrOfElements;
 
        private double pi;              // final result
        private int nrOfResults;        // keep track of how many results we have received back
        private final long start = System.currentTimeMillis();
 
        private final ActorRef listener;
        private final ActorRef workerRouter;
 
        // in constructor, we create a round-robin router to spread out the work evenly between the workers
        // 3.1.1) nrOfWorkers : how many workers we should start up
        // 3.1.2) nrOfMessages: how many number tasks to send out to the workers
        // 3.1.3) nrOfElements: how big the number tasks sent to each worker should be
        // 3.1.4) listener: ActorRef handle used to report the the final result to listener
        public Master(final int nrOfWorkers, int nrOfMessages, int nrOfElements, ActorRef listener) {
            this.nrOfMessages = nrOfMessages;
            this.nrOfElements = nrOfElements;
            this.listener = listener;
 
            workerRouter = this.getContext().actorOf(
                Props.create(Worker.class).withRouter(new RoundRobinPool(nrOfWorkers)),
                "workerRouter"
            );
        }
 
        // message handler
        // 3.2.1) Calculate: start the calculation when received this message
        // 3.2.1) Result   : aggregate to the final result, i.e. pi, when received this message 
        public void onReceive(Object message) {
            if (message instanceof Calculate) {
                for (int start = 0; start < nrOfMessages; start++) {
                    workerRouter.tell(new Work(start, nrOfElements), getSelf());
                }
            } else if (message instanceof Result) {
                Result result = (Result) message;
                pi += result.getValue();
                nrOfResults += 1;
                if (nrOfResults == nrOfMessages) { // when results received back matches the number of tasks sent
                    // Send the final result to the listener
                    Duration duration = Duration.create(System.currentTimeMillis() - start, TimeUnit.MILLISECONDS);
                    listener.tell(new PiApproximation(pi, duration), getSelf());
                    // Stops this actor and all its supervised children (actors)
                    // in this case,
                    //   one supervised actor, i.e. the router
                    //   the router has nrOfWorkers supervised actors
                    // stop() will propagate down to all its supervised children
                    getContext().stop(getSelf());
                }
            } else {
               unhandled(message);
            }
        }
    }

    // 4) Creating the result listener
    public static class Listener extends UntypedActor {

        public void onReceive(Object message) {
            // message handler
            //   PiApproximation: prints the result and shuts down the ActorSystem when received from Master
            // 4.2) Result   :
            if (message instanceof PiApproximation) {
                PiApproximation approximation = (PiApproximation) message;
                System.out.println(String.format("\n\tPi approximation: \t\t%s\n\tCalculation time: \t%s",
                approximation.getPi(), approximation.getDuration()));
                getContext().system().terminate();
            } else {
                unhandled(message);
            }
        }
    }
}
