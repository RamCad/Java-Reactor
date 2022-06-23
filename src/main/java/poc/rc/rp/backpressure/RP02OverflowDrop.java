package poc.rc.rp.backpressure;

import java.util.ArrayList;
import java.util.List;
import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class RP02OverflowDrop {
  public static void main(String[] args) {

    /**
     * Queues.class - SMALL_BUFFER_SIZE default is 256
     * to override
     * 75% of 16 = 12
     * Once 12th item is received, the next emitted item is pushed to buffer
     * System.setProperty("reactor.bufferSize.small", "16");
     */

    List<Object> list = new ArrayList<>();
    Flux.create(fluxSink -> {
      for (int i = 0; i < 350; i++) {
        fluxSink.next(i);
        System.out.println("Emitted: " + i);
      }
      fluxSink.complete();
    })
        .onBackpressureDrop(list::add) // after 256 its dropped why 256? check above
        .publishOn(Schedulers.boundedElastic())
        .doOnNext(o -> Util.sleepMillis(10))
        .subscribe(Util.getSubscriber());

    Util.sleepSeconds(45);
    System.out.println(list);// dropped items
  }
}
