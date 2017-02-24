// Future:
// what is wrong with future and callbacks?
// 1) we may need a dedicated error callback function in case an Exception occurs during processing
// 2) if starting a range of asynchronous tasks, we may fire a callback only when the last task has completed
// 3) callback hells: it becomes messy once we begin to chain callback functions
//    ex. if one callback triggers another asynchronous function which in turn takes its own callback, and so on
//        (rather common in the JavaScript / Node.js applications) 
// 4) a callback must not be called multiple times as it is to signal that the function has completed (only once)
//    but we’d have to implement this mechanism ourselves
// CompletableFuture/Promise:
// 1) a sensible mechanism to mitigate callback hell and write clean asynchronous code
//    it replaces callbacks which signal successful or exceptional completion of a function
//    (it must be fulfilled at most once)
// 2) it supports Lambdas (functional), parallelism (all its methods can run asyncrhonously) 
//    it is event driven so we can build systems in a non-blocking fashion
// Two most typical use cases of CompletableFuture/Promise:
//    useful whenever you want to collect the result of an asynchronous task or multiple asynchronous tasks
// 1) Parallel computing on multiple CPU cores
// 2) Remote method invocation through e.g. SOA or REST calls through a remote interface
//    it a means to specify what should be done with the output once it is received in a
//      functional, declarative and reactive programming style
// Some basics:
// 1) Fulfill a promise
//    explicitly fulfill a promise by calling complete()
//    register the listener with:
//      thenAccept(): takes the promise outcome as an argument
//      thenRun()   : takes a Runnable as the argument, no access to the promise outcome
// 2) Reject a promise
//    promise.exceptionally() / promise.completeExceptionally()
//    signal an error occurrence, i.e. the future did not complete normally but throws an exceptio
//    you wouldn’t let your actual task throw the exception; instead, you inform the CompletableFuture event listener
//      that an exception occurred

import java.util.concurrent.TimeUnit;
import java.util.concurrent.FutureTask;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutures1 {

    public static void main(String[] args) throws Exception {
        // 1) basic uses
        // 1.1) supplyAsync([function]): 
        //      return: a new CompletableFuture that is asynchronously completed by a task running in the ForkJoinPool.commonPool()
        CompletableFuture<String> future = CompletableFuture.supplyAsync(
            () -> { return "future task is complete"; }
        );
        // 1.2) get(): 
        //      it waits if necessary for the future to complete, and then returns its result
        System.out.println(future.get());

        // 2) future that never completes
        CompletableFuture<String> futureNeverComplete = CompletableFuture.supplyAsync(
            () -> {
                for (int i = 0; i >= 0; i++) {
                    System.out.println("task " + i);
                }
                return "futureNeverComplete is complete";
            }
        );
        // 2.1) getNow([fallback]):
        //      return: the fallback String if the result of the computation is not present yet 
        System.out.println(futureNeverComplete.getNow("futureNeverComplete is not complete yet"));
        // 2.2) get(long amount, TimeUnit unit):
        //      it waits x time units and then tries to return the computed value if available
        //      if not an java.util.concurrent.TimeoutException is thrown
        System.out.println(futureNeverComplete.get(1, TimeUnit.MILLISECONDS));
    }
}
