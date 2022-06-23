package poc.rc.rp.combinepublishers;

import java.time.Duration;
import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;

public class RP05CombineLatest {

  public static void main(String[] args) {
    Flux.combineLatest(getString(), getNumber(), (a, b) -> a+b)
        .subscribe(Util.getSubscriber());

    Util.sleepSeconds(10);
  }

  private static Flux<String> getString() {
    return Flux.just("A", "B", "C", "D")
        .delayElements(Duration.ofSeconds(1));
  }

  private static Flux<Integer> getNumber() {
    return Flux.just(1, 2, 3)
        .delayElements(Duration.ofSeconds(3));
  }
}
