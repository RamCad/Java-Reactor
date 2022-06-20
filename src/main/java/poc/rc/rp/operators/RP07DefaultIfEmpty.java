package poc.rc.rp.operators;

import poc.rc.rp.Util;
import reactor.core.publisher.Flux;

public class RP07DefaultIfEmpty {

  public static void main(String[] args) {
    getOrders()
        .filter(i -> i > 10)
        .defaultIfEmpty(100)
        .subscribe(Util.getSubscriber());
  }

  private static Flux<Integer> getOrders() {
    return Flux.range(1, 10);
  }
}
