package poc.rc.rp.backpressure;

import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class RP04OverflowError {
  public static void main(String[] args) {
    // 75% of 16 = 12
    System.setProperty("reactor.bufferSize.small", "16");
    Flux.create(fluxSink -> {
      for (int i = 0; i < 201 && !fluxSink.isCancelled(); i++) {
        fluxSink.next(i);
        Util.sleepMillis(1);
        System.out.println("Emitted: " + i);
      }
      fluxSink.complete();
    })
        .onBackpressureError()
        .publishOn(Schedulers.boundedElastic())
        .doOnNext(o -> Util.sleepMillis(10))
        .subscribe(Util.getSubscriber());

    Util.sleepSeconds(15);
  }
}
