package poc.rc.rp.repeatnretry;

import java.time.Duration;
import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

public class RP03RetryWhen {
  public static void main(String[] args) {
    getIntegers()
        .retryWhen(Retry.fixedDelay(2, Duration.ofSeconds(3)))
        .subscribe(Util.getSubscriber());
  }

  private static Flux<Integer> getIntegers() {
    return Flux.range(1, 3)
        .doOnSubscribe(subscription -> System.out.println("--Subscribed--"))
        .doOnComplete(() -> System.out.println("--Completed--"))
        .map(i -> i / (Util.faker().random().nextInt(1,5) > 3 ? 1 : 0))
        .doOnError(err -> System.out.println("--Error--"));
  }
}
