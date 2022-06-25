package poc.rc.rp.sinks;

import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.Many;

public class RP02SinkUnicast {

  public static void main(String[] args) {
    // handle through which we emit items
    Many<Object> sinkUnicast = Sinks.many().unicast().onBackpressureBuffer();

    // subscribe from here
    Flux<Object> flux = sinkUnicast.asFlux();

    flux.subscribe(Util.getSubscriber("sub 1"));
    flux.subscribe(Util.getSubscriber("sub 2")); // error - allows only single subscriber

    sinkUnicast.tryEmitNext("test 1 sink unicast");
    sinkUnicast.tryEmitNext("test 2 sink unicast");
    sinkUnicast.tryEmitComplete();

  }
}
