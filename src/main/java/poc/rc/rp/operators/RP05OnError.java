package poc.rc.rp.operators;

import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class RP05OnError {

  public static void main(String[] args) {
    Flux.range(1, 10)
        .log()
        .map(i -> 10/(5-i))
//        .onErrorReturn(-1) // fallback value
        .onErrorResume(throwable -> fallback())
        .subscribe(Util.getSubscriber());
  }

  private static Mono<Integer> fallback() {
    return Mono.fromSupplier(() ->
        Util.faker().random().nextInt(100, 200));
  }
}
