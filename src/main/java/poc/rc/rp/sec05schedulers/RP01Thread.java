package poc.rc.rp.sec05schedulers;

import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;

public class RP01Thread {

  public static void main(String[] args) {

    /**
     * Default behavior is everything runs on Main Thread
     */
    Flux<Object> flux = Flux.create(fluxSink -> {
      printCurrThread("create");
      fluxSink.next(1);
    })
        .doOnNext(i -> printCurrThread("next: " + i));

//    sec02flux.subscribe(v -> printCurrThread("subscriber: " + v));

    /**
     * Alternately we can run on different threads but should be managed
     * so we use scheduler methods provided by reactor
     */
    Runnable runnable = () -> flux.subscribe(v -> printCurrThread("sub " + v));

    for (int i = 0; i < 2; i++) {
      new Thread(runnable).start();
    }

    Util.sleepSeconds(5);

  }

  private static void printCurrThread(String msg) {
    System.out.println(msg + "\t\t Thread: " + Thread.currentThread().getName());
  }
}
