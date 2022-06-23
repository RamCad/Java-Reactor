package poc.rc.rp.backpressure;

import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class RP01Overflow {

  /**
   * Overflow Strategies
   * buffer - keep in memory    // Default behavior
   * drop - once the queue is full, new items will be dropped
   * latest - once the queue is full, keep 1 latest item as and when it arrives. drop old
   * error - throw error to the downstream
   */

  public static void main(String[] args) {
    Flux.create(fluxSink -> {
      for (int i = 0; i < 50; i++) {
        fluxSink.next(i);
        System.out.println("Emitted: " + i);
        System.out.println(Thread.currentThread().getName());
      }
      fluxSink.complete();
    })
        .publishOn(Schedulers.boundedElastic())
        .doOnNext(o -> {
          Util.sleepMillis(10);
          System.out.println(Thread.currentThread().getName());
        })
        .subscribe(Util.getSubscriber());

    Util.sleepSeconds(45);
  }
}
