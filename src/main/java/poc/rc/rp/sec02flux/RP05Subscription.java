package poc.rc.rp.sec02flux;

import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;

public class RP05Subscription {

  public static void main(String[] args) {

    AtomicReference<Subscription> atomicReference = new AtomicReference<>();

    Flux.range(1, 20)
        .log()
        .subscribeWith(new Subscriber<Integer>() {
          @Override
          public void onSubscribe(Subscription subscription) {
            System.out.println("Subscription: " + subscription);
            atomicReference.set(subscription);
          }

          @Override
          public void onNext(Integer integer) {
            System.out.println("onNext: " + integer);
          }

          @Override
          public void onError(Throwable throwable) {
            System.out.println("onError: " + throwable.getMessage());
          }

          @Override
          public void onComplete() {
            System.out.println("onComplete");
          }
        });

    Util.sleepSeconds(3);
    atomicReference.get().request(3);

    Util.sleepSeconds(3);
    atomicReference.get().request(4);

    Util.sleepSeconds(3);
    atomicReference.get().cancel();

    Util.sleepSeconds(3);
    atomicReference.get().request(3);
  }
}
