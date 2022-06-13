package poc.rc.rp.flux;

import poc.rc.rp.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class RP08FluxFromMono {

  public static void main(String[] args) {
    Mono<String> mono = Mono.just("abc");

    Flux<String> flux = Flux.from(mono);

    flux.subscribe(Util.onNext());

    // Flux to Mono - only emits 1 item
    Flux.range(1,10)
        .next()
        .subscribe(Util.onNext());
  }

}
