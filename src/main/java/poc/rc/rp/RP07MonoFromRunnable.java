package poc.rc.rp;

import reactor.core.publisher.Mono;

public class RP07MonoFromRunnable {

  public static void main(String[] args) {

    // fromSupplier, fromCallable, fromFuture - supplies some value but
    // Runnable Interface doesn't accept parameters and does't return anything
    Runnable runnable = () -> System.out.println("");

    // Use case - If there is time consuming operation
    // just to notify about it when it's completed
    Mono.fromRunnable(runnable)
        .subscribe(Util.onNext(),
            Util.onError(),
            Util.onComplete());

    // Example
    Mono.fromRunnable(timeTakingProcess())
        .subscribe(Util.onNext(),
            Util.onError(),
            Util.onComplete());
  }

  private static Runnable timeTakingProcess() {
    return () -> {
      Util.sleepSeconds(3);
      System.out.println("TimeTakingProcess Completed!");
    };
  }
}
