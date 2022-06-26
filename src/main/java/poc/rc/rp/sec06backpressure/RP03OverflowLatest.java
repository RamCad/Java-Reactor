package poc.rc.rp.sec06backpressure;

import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class RP03OverflowLatest {
  public static void main(String[] args) {
    // 75% of 16 = 12
    System.setProperty("reactor.bufferSize.small", "16");
    Flux.create(fluxSink -> {
      for (int i = 0; i < 350; i++) {
        fluxSink.next(i);
        System.out.println("Emitted: " + i);
        Util.sleepMillis(1);
      }
      fluxSink.complete();
    })
        .onBackpressureLatest()
        .publishOn(Schedulers.boundedElastic())
        .doOnNext(o -> Util.sleepMillis(10))
        .subscribe(Util.getSubscriber());

    Util.sleepSeconds(15);
  }
}
