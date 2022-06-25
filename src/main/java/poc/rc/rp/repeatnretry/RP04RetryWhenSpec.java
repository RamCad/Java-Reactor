package poc.rc.rp.repeatnretry;

import java.time.Duration;
import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

public class RP04RetryWhenSpec {
  public static void main(String[] args) {
    orderService(Util.faker().business().creditCardNumber())
        .retryWhen(Retry.from(
            retrySignalFlux -> retrySignalFlux
            .doOnNext(rs -> {
              System.out.println(rs.totalRetries());
              System.out.println(rs.failure());
            })
            .handle(((retrySignal, objectSynchronousSink) -> {
              if(retrySignal.failure().getMessage().equals("500")) {
                objectSynchronousSink.next(1);
              } else {
                objectSynchronousSink.error(retrySignal.failure());
              }
            }))
            .delayElements(Duration.ofSeconds(1))
        ))
        .subscribe(Util.getSubscriber());

  }

  private static Mono<String> orderService(String num) {
    return Mono.fromSupplier(() -> {
      processPayment(num);
      return Util.faker().idNumber().valid();
    });
  }

  private static void processPayment(String num) {
    int random = Util.faker().random().nextInt(1, 10);

    if (random < 8){
      throw new RuntimeException("500");
    } else if (random < 10) {
      throw new RuntimeException("404");
    }
  }
}
