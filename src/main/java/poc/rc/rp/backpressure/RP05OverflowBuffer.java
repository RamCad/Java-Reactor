package poc.rc.rp.backpressure;

import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class RP05OverflowBuffer {
  public static void main(String[] args) {
    // 75% of 16 = 12
    System.setProperty("reactor.bufferSize.small", "16");
    Flux.create(fluxSink -> {
      for (int i = 0; i < 201 && !fluxSink.isCancelled(); i++) {
        fluxSink.next(i);
        System.out.println("Emitted: " + i);
        Util.sleepMillis(1);
      }
      fluxSink.complete();
    })
//        .onBackpressureBuffer(20) // control the buffer size
        .onBackpressureBuffer(20, o -> System.out.println("Dropped: " + o)) // there might be cases where there was delay in receiving the signal and publisher emitted more items
        .publishOn(Schedulers.boundedElastic())
        .doOnNext(o -> Util.sleepMillis(10))
        .subscribe(Util.getSubscriber());

    Util.sleepSeconds(15);
  }
}
