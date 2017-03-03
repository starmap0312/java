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
import akka.dispatch.Futures;

import scala.concurrent.duration.Duration;

import scala.compat.java8.JFunction;

class DangerousActor extends UntypedActor {
    private final CircuitBreaker breaker;

    public DangerousActor() {
        this.breaker = new CircuitBreaker(
            getContext().dispatcher(),
            getContext().system().scheduler(),
            2,                                 // maximum failures = 2    times: after 2 failures circuit will be open for 10 seconds
            Duration.create(3, "seconds"),     // call timeout     = 3  seconds: after 3 secs a request is seen as failed
            Duration.create(10, "seconds")     // reset timeout    = 10 seconds: circuit stays in open state for 10 seconds each time
                                               //                                after 10 secs it enters half-open state for a re-try
        ).onOpen(
            new Runnable() {
                @Override
                public void run() {
                    System.out.println("onOpen(): 2 failures exceed timeout of 3 seconds, so the circuit is now open for 10 seconds");
                }
            }
        ).onHalfOpen(
            new Runnable() {
                @Override
                public void run() {
                    System.out.println("onHalfOpen(): reset timeout of 10 seconds has passed, so the circuit is now half-open");
                }
            }
        ).onClose(
            new Runnable() {
                @Override
                public void run() {
                    System.out.println("onClose(): the circuit is now close after a successful retry at half-open state");
                }
            }
        );
    }

    public String dangerousCall() {
        try {
            Thread.sleep(5000);                 // sleep 5 seconds
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        System.out.println("  dangerous call(): respond after 5 seconds");
        return "a dangerous call";
    }

    @Override
    public void onReceive(Object message) {
        if (message instanceof String) {
            String msg = (String) message;
            if ("async request".equals(msg)) {
                System.out.println("received 1 async request");
                breaker.callWithCircuitBreaker(
                    () -> Futures.future(
                        () -> dangerousCall(),
                        getContext().dispatcher()
                    )
                ).onComplete(
                    JFunction.func(
                        (future) -> {
                            System.out.println("onComplete: future.isFailure()? " + future.isFailure());
                            return null;
                        }
                    ),
                    getContext().dispatcher().prepare()
                );
            }
            if ("sync request".equals(msg)) {
                System.out.println("received 1 sync request (blocking)");
                breaker.callWithSyncCircuitBreaker(() -> dangerousCall());
            }
        }
    }
}

public class AkkaCircuitBreaker {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("CircuitBreakerSystem");
        ActorRef master = system.actorOf(Props.create(DangerousActor.class));
        master.tell("async request", ActorRef.noSender()); 
        master.tell("async request", ActorRef.noSender()); 
        try {
            Thread.sleep(8000);                 // sleep 8 seconds
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        System.out.println("(the following two calls will never be made due to the circuit is open now)");
        master.tell("async request", ActorRef.noSender()); 
        master.tell("async request", ActorRef.noSender()); 
        try {
            Thread.sleep(10000);                 // sleep 10 seconds
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        master.tell("async request", ActorRef.noSender()); 
        //system.terminate();
    }
}

