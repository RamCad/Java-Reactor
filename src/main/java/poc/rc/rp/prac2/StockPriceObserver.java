package poc.rc.rp.prac2;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import poc.rc.rp.Util;
import reactor.core.publisher.Flux;

public class StockPriceObserver {

  public static void main(String[] args) throws InterruptedException {

    CountDownLatch latch = new CountDownLatch(1);

    getPrice().subscribeWith(new Subscriber<Integer>() {

      private Subscription subscription;

      @Override
      public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(Long.MAX_VALUE);
      }

      @Override
      public void onNext(Integer price) {
        System.out.println(LocalDateTime.now() + " Price: " + price);
        if(price > 110 || price < 90) {
          this.subscription.cancel();
          latch.countDown();
        }
      }

      @Override
      public void onError(Throwable throwable) {
        latch.countDown();
      }

      @Override
      public void onComplete() {
        latch.countDown();
      }
    });

    latch.await();
  }

  public static Flux<Integer> getPrice() {
    AtomicInteger atomicInteger = new AtomicInteger(100);

    return Flux.interval(Duration.ofSeconds(1))
        .map(i -> atomicInteger.getAndAccumulate(Util.faker().random().nextInt(-5, 5),
            Integer::sum));
  }
}
