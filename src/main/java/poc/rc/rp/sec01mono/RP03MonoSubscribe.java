package poc.rc.rp.sec01mono;

import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Mono;

public class RP03MonoSubscribe {

  public static void main(String[] args) {

    //publisher
    // use .just() only when you have data available
    Mono<Integer> mono = Mono.just("test")
        .map(String::length)
        .map(i -> i/0);

    // Multiple overridden methods are available for subscribe
    // 1 - publisher emits but no consumer
//    sec01mono.subscribe();

    // 2
    mono.subscribe(
        Util.onNext(),
        Util.onError(),
        Util.onComplete()
    );
  }
}
