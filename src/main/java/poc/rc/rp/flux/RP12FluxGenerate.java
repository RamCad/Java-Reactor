package poc.rc.rp.flux;

import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;

public class RP12FluxGenerate {

  public static void main(String[] args) {
    /**
     * not allowed to issue more than 1 item
     * fluxSink - only 1 instance (loop should be maintained)
     * synchronousSink - generate handles loop - invokes next() again and again
     */
    Flux.generate(synchronousSink -> {
      synchronousSink.next(Util.faker().country().name());
//      synchronousSink.next(Util.faker().country().name()); // not allowed
    })
        .take(2)
        .subscribe(Util.getSubscriber());

    // Example
    Flux.generate(synchronousSink -> {
      String country = Util.faker().country().name();
      synchronousSink.next(country);
      if(country.equalsIgnoreCase("canada")) {
        synchronousSink.complete();
      }
    })
        .subscribe(Util.getSubscriber());


    // Example - state

    Flux.generate(() -> 1, (counter, sink) -> {
      String country = Util.faker().country().name();
      sink.next(country);
      if(country.equalsIgnoreCase("canada") || counter == 10) {
        sink.complete();
      }
      return counter + 1;
    })
        .subscribe(Util.getSubscriber());
  }
}
