package poc.rc.rp.repeatnretry;

import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;

public class RP02Retry {

  public static void main(String[] args) {
    getIntegers()
        .retry(2)
        .subscribe(Util.getSubscriber());
  }

  private static Flux<Integer> getIntegers() {
    return Flux.range(1, 3)
        .doOnSubscribe(subscription -> System.out.println("--Subscribed--"))
        .doOnComplete(() -> System.out.println("--Completed--"))
        .map(i -> i / (Util.faker().random().nextInt(1,5) > 3 ? 0 : 1))
        .doOnError(err -> System.out.println("--Error--"));
  }
}
