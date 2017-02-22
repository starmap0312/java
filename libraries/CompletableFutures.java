// Future:
// 1) the pending result of an asynchronous computation
// 2) use get() method to get the result of the computation when it's done
// 3) problem: a call to get is blocking until the computation is done
//             this makes the asynchronous computation pointless
// CompletableFuture/Promise:
// 1) it not only implementing the Future interface but also the CompletionStage interface
// 2) a CompletionStage is a "promise": it promises that the computation eventually will be done
// 3) advantages: it offers lots of methods that let you attach callbacks that will be executed on completion
//                so we can build systems in a non-blocking fashion
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutures {

    public static void main(String[] args) throws Exception {
        // 1) Completable:
        //    conceptually, it executes runnable.run() and then future.complete(null) in a worker thread
        //      when the caller calls get(), it blocks until the future is completed
        //    CompletableFuture.runAsync([Runnable runnable]):
        //      return  : a new CompletableFuture that is asynchronously completed by a task running in the ForkJoinPool.commonPool()
        //                after it runs the given action
        //      runnable: the action to run before completing the returned CompletableFuture
        System.out.println("Example 1");
        CompletableFuture<Void> future = CompletableFuture.runAsync(
            () -> {
                try {
                    Thread.sleep(1000);
                    System.out.println("hello");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        );
        future.get();                // blocking, it may throw ExecutionException or InterruptedException
        System.out.println("world");

        // 2) Listenable: 
        //    whenComplete([action]):
        //      it executes the given action when this stage completes
        //        the returned stage is completed when the action returns
        //        if the supplied action itself encounters an exception, then the returned stage exceptionally completes with
        //        this exception unless this stage also completed exceptionally
        //      return: the new CompletionStage
        //      action: the action to perform
        System.out.println("Example 2");
        CompletableFuture.runAsync(
            () -> {
                try {
                    Thread.sleep(1000);
                    System.out.println("hello");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        ).whenComplete(
            (result, throwable) -> { System.out.println("world"); }
        );
        future.get();                // blocking, it may throw ExecutionException or InterruptedException
    }
}
