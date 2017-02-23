// Future:
// 1) the pending result of an asynchronous computation
// 2) use get() method to get the result of the computation when it's done
//    the call is blocking until the computation is done
//    this makes the asynchronous computation pointless
// 3) it decouples future task execution from the callback
//    once the future task computation is finished, the callback is invoked with the computation result
// CompletableFuture/Promise:
// 1) a Promise is a Future that may be explicitly completed
//    whereas, a Future represents the result of a computation which may or may not be completed in the future
// 2) it not only implementing the Future interface but also the CompletionStage interface
// 3) a CompletionStage is a "promise": it promises that the computation eventually will be done
// 4) advantages: it offers lots of methods that let you attach callbacks that will be executed on completion
//                so we can build systems in a non-blocking fashion

import java.util.concurrent.FutureTask;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutures3 {

    public static void main(String[] args) throws Exception {
        // 1) runAsync([Runnable runnable]):
        //    returns: a new CompletableFuture that is asynchronously completed by a task running in the ForkJoinPool.commonPool()
        //             after it runs the given action
        //    runnable: the action to run before completing the returned CompletableFuture
        //    conceptually, it executes runnable.run() and then future.complete(null) in a worker thread
        System.out.println("Example 1:");
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(
            () -> {
                System.out.println("Do runAsync task");
            }
        );
        // the caller is blocked until the future is completed (may throw ExecutionException or InterruptedException)
        System.out.println(future1.get());

        // 2) whenComplete([action]):
        //    returns: a new CompletionStage that executes the given action when this stage completes
        System.out.println("Example 2:");
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(
            () -> {
                System.out.println("Do runAsync task");
            }
        ).whenComplete(
            (result, throwable) -> {
                System.out.println("Do whenComplete task");
            }
        );
    }
}
