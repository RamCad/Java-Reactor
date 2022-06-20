package poc.rc.rp.flux;

import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;

public class RP01Flux {

  public static void main(String[] args) {
    Flux<Integer> flux = Flux.just(1, 2, 3);

    Flux<Object> flux1 = Flux.just(1, "a", 1.0, Util.faker().name().fullName());

//    flux.subscribe();

    flux.subscribe(Util.onNext(), Util.onError(), Util.onComplete());

    flux1.subscribe(Util.onNext(), Util.onError(), Util.onComplete());
  }

}
