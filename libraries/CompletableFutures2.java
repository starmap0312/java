// Java CompletableFuture (Promise)
// 1) Fulfill a promise
//    explicitly fulfill a promise by calling complete()
//    register the listener with:
//      thenAccept(): takes the promise outcome as an argument
//      thenRun()   : takes a Runnable as the argument, no access to the promise outcome
// 2) Reject a promise
//    promise.exceptionally() / promise.completeExceptionally()
//    signal an error occurrence, i.e. the future did not complete normally but throws an exception
//    you wouldn’t let your actual task throw the exception; instead, you inform the CompletableFuture event listener
//      that an exception occurred
import java.util.concurrent.TimeUnit;
import java.util.concurrent.FutureTask;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutures2 {

    public static void main(String[] args) throws Exception {
        // 1) thenApply([function]):
        //    returns a new CompletionStage that, when this stage completes normally, is executed with this stage's result as the argument to the supplied function (Function)
        // 2) thenAccept([action]):
        //    returns a new CompletionStage that, when this stage completes normally, is executed with this stage's result as the argument to the supplied action (Consumer)
        System.out.println("Example 1:");
        CompletableFuture future = CompletableFuture.supplyAsync(
            () -> {
                System.out.println("Do supplyAsync task");
                return "Step 1 return";
            }
        ).thenApply(  // a Function
            (String result) -> {
                System.out.println("Got supplyAsync's return: " + result);
                System.out.println("Do thenApply task");
                return "Step 2 return";
            }
        ).thenAccept( // a Consumer
            (String result) -> {
                System.out.println("Got thenApply's return: " + result);
                System.out.println("Do thenAccept task");
            }
        );

        // 3) thenCompose([function]):
        //    returns a new CompletionStage that, when this stage completes normally, is executed with this stage as the argument to the supplied function
        System.out.println("Example 2:");
        CompletableFuture future2 = CompletableFuture.supplyAsync(
            () -> {
                System.out.println("Do supplyAsync task");
                return "Step 1 return";
            }
        ).thenCompose(
            (String result) -> CompletableFuture.supplyAsync(
                () -> {
                    System.out.println("Got supplyAsync's return: " + result);
                    System.out.println("Do thenCompose task");
                    return "Step 2 return";
                }
            )
        );
    }
}
