// Future:
// 1) the pending result of an asynchronous computation
// 2) use get() method to get the result of the computation when it's done
//    the call is blocking until the computation is done
//    this makes the asynchronous computation pointless
// 3) it decouples future task execution from the callback
//    once the future task computation is finished, the callback is invoked with the computation result
// CompletableFuture/Promise:
// 1)  a Future represents the result of a computation which may or may not be completed in the future, and
//     a Promise is a Future that may be explicitly completed
// 2) CompletableFuture not only implements the Future interface but also implements the CompletionStage interface
//
//    public interface Future<V> {
//        boolean isDone();
//        V get() throws InterruptedException, ExecutionException;
//    }
//
//    public interface CompletionStage<T> {
//        public <U> CompletionStage<U> thenApply(Function<? super T,? extends U> fn);
//        public CompletionStage<Void> thenAccept(Consumer<? super T> action);
//        public CompletionStage<Void> thenAccept(Consumer<? super T> action);
//        public <U> CompletionStage<U> thenCompose(Function<? super T, ? extends CompletionStage<U>> fn);
//    }
// 3) a CompletionStage is a "promise": it promises that the computation eventually will be done
// 4) advantages: it offers lots of methods that let you attach callbacks that will be executed on completion
//                so we can build systems in a non-blocking fashion
import java.util.concurrent.FutureTask;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutures1 {

    public static void main(String[] args) throws Exception {
        // 1) runAsync([Runnable runnable]):
        //    returns a new CompletableFuture that is asynchronously completed by a task running in the ForkJoinPool
        //    runnable: the action to run before completing the returned CompletableFuture
        //    conceptually, it executes runnable.run() and then future.complete(null) in a worker thread
        // 2) whenComplete([action]):
        //    returns a new CompletionStage with the same result or exception as this stage, that executes the given action when this stage completes
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
