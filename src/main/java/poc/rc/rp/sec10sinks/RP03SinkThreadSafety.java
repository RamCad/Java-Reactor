package poc.rc.rp.sec10sinks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.Many;

public class RP03SinkThreadSafety {

  public static void main(String[] args) {
    /**
     * Documentation says Sinks are thread safe
     * but from below ex (1st for loop) - it doesn't seem to be reliable
     * (2nd for loop) we handle error scenario and retry - looks thread safe
     */

    Many<Object> sink = Sinks.many().unicast().onBackpressureBuffer();

    Flux<Object> flux = sink.asFlux();
    List<Object> list = new ArrayList<>();

    flux.subscribe(list::add);

//    for (int i = 0; i < 1000; i++) {
//      final int j = i;
//      CompletableFuture.runAsync(() -> {
//        sink.tryEmitNext(j);
//      });
//    }

    for (int i = 0; i < 1000; i++) {
      final int j = i;
      CompletableFuture.runAsync(() -> {
        sink.emitNext(j, (s, e) -> true);
      });
    }

    Util.sleepSeconds(3);
    System.out.println(list.size());
  }
}
