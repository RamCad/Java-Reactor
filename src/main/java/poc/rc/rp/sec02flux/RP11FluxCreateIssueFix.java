package poc.rc.rp.sec02flux;

import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;

public class RP11FluxCreateIssueFix {

  public static void main(String[] args) {
    /**
     * only one instance of fluxsink
     * after 3 subscriber is not receiving any data but source keeps on emitting data
     * use - fluxSink.isCancelled()
     */
    Flux.create(fluxSink -> {
      String country;
      do {
        country = Util.faker().country().name();
        System.out.println("Emitting: " + country);
        fluxSink.next(country);
      } while(!country.equalsIgnoreCase("canada") && !fluxSink.isCancelled());
      fluxSink.complete();
    })
        .take(3)
        .subscribeWith(Util.getSubscriber());
  }
}
