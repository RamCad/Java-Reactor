package poc.rc.rp.prac5;

import java.time.Duration;
import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;

public class CarPriceOverTime {

  public static void main(String[] args) {
    int carPrice = 10000;
    Flux.combineLatest(monthStream(), demandStream(), (m, d) -> (carPrice - (m * 100)) * d)
        .subscribe(Util.getSubscriber());

    Util.sleepSeconds(30);
  }

  private static Flux<Long> monthStream() {
    return Flux.interval(Duration.ZERO, Duration.ofSeconds(1));
  }

  private static Flux<Double> demandStream() {
    return Flux.interval(Duration.ofSeconds(3))
        .map(i -> Util.faker().random().nextInt(80, 120) / 100d)
        .startWith(1d);
  }
}
