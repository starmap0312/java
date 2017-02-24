// Futures and promises are non-blocking
//   they make use of callbacks instead of typical blocking operations
//   they facilitate callbacks on a higher-level: ex. combinators such as flatMap, foreach, and filter
//     this composes futures in a non-blocking way
// Future:
//   a placeholder object that you create for a result that doesn’t yet complete (exist)
// What is wrong with future and callbacks?
// 1) we may need a dedicated error callback function in case an Exception occurs during processing
// 2) callback hells: it becomes messy once we begin to chain callback functions
//    ex. if one callback triggers another asynchronous function which in turn takes its own callback, and so on
//        (rather common in the JavaScript / Node.js applications) 
// 3) we need to make sure a callback is called multiple times by ourselves 
// 4) if there are several asynchronous tasks in sequence, we may want a callback only when the last task has completed
// Promise/CompletableFuture:
// 1) a sensible mechanism to mitigate callback hell and write clean asynchronous code
//    it replaces callbacks which signal successful or exceptional completion of a function
//    (it must be fulfilled at most once)
// 2) it supports Lambdas (functional), parallelism (all its methods can run asyncrhonously) 
//    it is event driven so we can build systems in a non-blocking fashion
// Two most typical use cases of Promise/CompletableFuture:
//   when you want to collect the result of an asynchronous task or multiple asynchronous tasks
// 1) Parallel computing on multiple CPU cores
// 2) Remote method invocation through e.g. SOA or REST calls through a remote interface
//    it a means to specify what should be done with the output once it is received in a
//      functional, declarative and reactive programming style
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
