// Circuit Breaker
// 1) used to provide stability and prevent cascading failures in distributed systems
// 2) circuit breaker mitigates the problem by detecting slow or failed dependencies, and stops the sending of further requests
// ex.
//   a web application depends on (interacting with) a remote third party web service (or database)
//   assume that the third party web service fail and could not reply in time
//     then users of our web application may re-submit their requests
//   this adds more requests to our web serivce, which may eventually cause the whole web application downtime
//     due to resource exhaustion, impacting all other users
//   circuit breakers aims to make the requests to fail-fast, letting user know that something went wrong
//     so that they will not re-submit their request
//     the failure behavior is confined to only those users that are using the third party web serivce
// 3) circuit breaker periodically retries the dependency
//    it waits for predefined amount of time to see if the dependency is available again
//    without circuit breaker, the server would continue to use unavailable dependency
//    users would see pages timing out or returning an error after many seconds
// 4) diagram:
//    
//   users <----> our web application <----> circuit breaker <----> third party web service (or database)
//                                    (if dependency is failing, then it only tries periodically to see if it is back)
//
// 5) Akka circuit breaker
//    it monitors timeouts and failures of requests to the third party web service
//    when the circuit is open, it wll fail user responses immediately, telling users to come back later
//    i.e. circuit will be open after a predefined number of consecutive failures
//           failed request is determined by configurable timeout
//         the circuit will remain open for a predefined amount of time
//    after waiting a period of time for recovery, the circuit will be half-open
//      it retries a request to see if dependency has recovered
//    
// 6) benefits:
//    we don’t make users wait for an error (fail fast: responsive)
//    we let the third party web service get a chance to heal (resiliency)

import akka.actor.UntypedActor;
import akka.actor.ActorSystem;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.pattern.CircuitBreaker;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import static akka.pattern.Patterns.pipe;
import static akka.dispatch.Futures.future;

import scala.concurrent.duration.Duration;

class DangerousActor extends UntypedActor {
    private final CircuitBreaker breaker;
    //private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public DangerousActor() {
        this.breaker = new CircuitBreaker(
            getContext().dispatcher(),
            getContext().system().scheduler(),
            2,                               // 2 maximum failures: after that the circuit will be open
            Duration.create(3, "seconds"),   // call timeout of 3 seconds: after that a request is taken as a failure
            Duration.create(10, "seconds")   // reset timeout of 10 seconds: the circuit will remain open for 10 seconds 
                                             //   after that it will enter half-open state for a re-try
        ).onOpen(
            new Runnable() {
                public void run() {
                    //log.warning("CircuitBreaker is now open and will not close for 1 minute");
                    System.out.println("circuit is now open for 1 minute");
                }
            }
        );
    }

    public String dangerousCall() {
        return "This really isn't that dangerous of a call after all";
    }

    @Override
    public void onReceive(Object message) {
        if (message instanceof String) {
            String msg = (String) message;
            if ("is my middle name".equals(msg)) {
                pipe(
                    breaker.callWithCircuitBreaker(
                        () -> future(
                            () -> dangerousCall(),
                            getContext().dispatcher()
                        )
                    ),
                    getContext().dispatcher()
                ).to(
                    getSender()
                );
            }
            if ("block for me".equals(msg)) {
                System.out.println("enter block for me");
                getSender().tell(
                    breaker.callWithSyncCircuitBreaker(
                        () -> dangerousCall()
                    ),
                    getSelf()
                );
            }
        }
    }
}

public class AkkaCircuitBreaker {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("CircuitBreakerSystem");
        ActorRef master = system.actorOf(Props.create(DangerousActor.class));
        master.tell("block for me", ActorRef.noSender()); 
        system.terminate();
    }
}

