package poc.rc.rp.sec09repeatnretry;

import java.util.concurrent.atomic.AtomicInteger;
import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;

public class RP01Repeat {

  private static AtomicInteger atomicInteger = new AtomicInteger(1);

  public static void main(String[] args) {
    getIntegers()
//        .repeat(2) // 2 more times (2+1)
        .repeat(() -> atomicInteger.get() < 14) // boolean supplier
        .subscribe(Util.getSubscriber());
  }

  private static Flux<Integer> getIntegers() {
    return Flux.range(1, 3)
        .doOnSubscribe(subscription -> System.out.println("--Subscribed--"))
        .doOnComplete(() -> System.out.println("--Completed--"))
        .map(i -> atomicInteger.getAndIncrement());
  }
}
