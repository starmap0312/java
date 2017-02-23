import java.util.concurrent.TimeUnit;
import java.util.concurrent.FutureTask;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutures2 {

    public static void main(String[] args) throws Exception {
        // 1) thenApply([function]):
        //    returns: a new CompletionStage that is executed with previous stage's result as the argument to the supplied function
        // 2) thenAccept([function]):
        //    returns a new CompletionStage that is executed with previous stage's result as the argument to the supplied function 
        System.out.println("Example 1:");
        CompletableFuture future = CompletableFuture.supplyAsync(
            () -> {
                System.out.println("Do supplyAsync task");
                return "Step 1 return";
            }
        ).thenApply(
            (String result) -> {
                System.out.println("Got supplyAsync's return: " + result);
                System.out.println("Do thenApply task");
                return "Step 2 return";
            }
        ).thenAccept(
            (String result) -> {
                System.out.println("Got thenApply's return: " + result);
                System.out.println("Do thenAccept task");
            }
        );

        // 3) thenCompose([function]):
        //    returns: a new CompletionStage that is executed with this stage as the argument to the supplied function
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
