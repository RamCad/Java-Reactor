package poc.rc.rp.sec08batching;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;
import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class RP03Window {

  private static AtomicInteger atomicInteger = new AtomicInteger(1);

  /**
   * similar to buffer but returns sec02flux instead of list
   */
  public static void main(String[] args) {
    events()
//        .window(5) // returns sec02flux
        .window(Duration.ofSeconds(2))
        .flatMap(RP03Window::processEvents)
        .subscribe(Util.getSubscriber());

    Util.sleepSeconds(40);
  }

  private static Mono<Integer> processEvents(Flux<String> flux) {
    return flux.doOnNext(i -> System.out.println("received: " + i))
        .doOnComplete(() -> {
          System.out.println("completed batch");
          System.out.println("----------------");
        })
        .then(Mono.just(atomicInteger.getAndIncrement()));
  }

  private static Flux<String> events() {
    return Flux.interval(Duration.ofMillis(500))
        .map(i -> "event: " + i);
  }
}
