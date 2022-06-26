package poc.rc.rp.sec03operators;

import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;

public class RP08SwitchIfEmpty {

  public static void main(String[] args) {
    getOrders()
        .filter(i -> i> 10)
        .switchIfEmpty(fallback()) //
        .subscribe(Util.getSubscriber());
  }

  private static Flux<Integer> fallback() {
    return Flux.range(20, 5);
  }

  private static Flux<Integer> getOrders() {
    return Flux.range(1, 10);
  }
}
