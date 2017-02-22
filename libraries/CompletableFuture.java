// Future:
// 1) the pending result of an asynchronous computation
// 2) use get() method to get the result of the computation when it's done
// 3) problem: a call to get is blocking until the computation is done
//             this makes the asynchronous computation pointless
// CompletableFuture:
// 1) it not only implementing the Future interface but also the CompletionStage interface
// 2) a CompletionStage is a "promise": it promises that the computation eventually will be done
// 3) advantages: it offers lots of methods that let you attach callbacks that will be executed on completion
//                so we can build systems in a non-blocking fashion

public class CompletableFuture {

    public static void main(String[] args) {
    }
}
