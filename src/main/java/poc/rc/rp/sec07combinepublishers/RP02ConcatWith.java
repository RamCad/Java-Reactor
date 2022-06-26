package poc.rc.rp.sec07combinepublishers;

import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;

public class RP02ConcatWith {

  public static void main(String[] args) {
    Flux<String> flux1 = Flux.just("a", "b");
    Flux<String> flux2 = Flux.just("c", "d", "e");

    Flux<String> flux = flux1.concatWith(flux2); // Flux.concat(flux1, flux2);

    flux.subscribe(Util.getSubscriber());

    // Ex
    Flux<String> flux3 = Flux.error(new RuntimeException("Error!"));

    Flux<String> fluxErr = Flux.concat(flux1, flux3, flux2);// throws error
    /**
     * Can delay using
     * Flux.concatDelayError(flux1, flux3, flux2);
     */
    fluxErr.subscribe(Util.getSubscriber());
  }
}
