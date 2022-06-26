package poc.rc.rp.sec03operators;

import java.time.Duration;
import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;

public class RP06Timeout {

  public static void main(String[] args) {
    getOrders()
        .timeout(Duration.ofSeconds(2), fallback())
        .subscribe(Util.getSubscriber());

    Util.sleepSeconds(60);
  }

  private static Flux<Integer> fallback() {
    return Flux.range(100, 10)
        .delayElements(Duration.ofMillis(200));
  }

  private static Flux<Integer> getOrders() {
    return Flux.range(1, 10)
        .delayElements(Duration.ofSeconds(5));
  }
}
