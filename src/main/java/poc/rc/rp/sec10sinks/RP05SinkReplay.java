package poc.rc.rp.sec10sinks;

import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.Many;

public class RP05SinkReplay {
  public static void main(String[] args) {
    // handle through which we emit items
    // more of cache behavior
    Many<Object> sink = Sinks.many().replay().all();

    // subscribe from here
    Flux<Object> flux = sink.asFlux();


    sink.tryEmitNext("test 1 sink replay");
    flux.subscribe(Util.getSubscriber("sub 1"));
    flux.subscribe(Util.getSubscriber("sub 2"));
    sink.tryEmitNext("test 2 sink replay");
    sink.tryEmitComplete();

  }
}
