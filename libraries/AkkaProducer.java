// Akka Actor Model
//   a higher level of abstraction for writing concurrent and distributed systems
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.UntypedActor;
import java.util.concurrent.TimeUnit;

class Consumer extends UntypedActor {
    // UntypedActor:
    //   it is the Java cousin to the Actor Scala interface
 
    @Override
    public void onReceive(Object msg) throws Exception {
        if(msg instanceof Integer) {
            System.out.println("<<< Consumer: Receiving & printing " + msg);
        }
    }
}

public class AkkaProducer {
 
    public static void main(String[] args) {
        // ActorSystem:
        //   it extends ActorRefFactory
        //   it is a hierarchical group of actors which share common configuration like dispatchers, 
        //      deployments, remote capabilities and addresses
        //   it is an entry point for creating or looking up actors
        //
        // Step 1) create an ActorSystem to look up actors
        ActorSystem system = ActorSystem.create("generate-numbers-one-to-ten");

        // ActorRef:
        //   it is an immutable and serializable handle to an actor
        //   it is thread-safe and fully shareable
        // Props:
        //   it is a configuration object used for creating an Actor
        //
        // Step 2) create an ActorRef handle based on the Consumer class (extends UntypedActor)
        ActorRef printNumbersConsumer = system.actorOf(Props.create(Consumer.class));

        // tell():
        //   it means “fire-and-forget”
        //     ex. send a message asynchronously and return immediately 
        // ActorRef.noSender():
        //   it is used when you are not inside an actor or do not want to pass the sender
        for (int i = 1; i <= 10; i++) {
            System.out.println(">>> Producer: Producing & sending a number " +  i);
            printNumbersConsumer.tell(i, ActorRef.noSender());
        }
        // Step 3) terminate the ActorSystem 
        system.terminate();
        System.out.println("===== Finished producing & sending numbers 1 to 10");
    }
}
