package poc.rc.rp.batching;

import java.time.Duration;
import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;

public class RP04Group {

  public static void main(String[] args) {
    Flux.range(1, 30)
        .delayElements(Duration.ofSeconds(1))
        .groupBy(i -> i % 2)
        .subscribe(groupedFlux -> processEvents(groupedFlux, groupedFlux.key()));

    Util.sleepSeconds(30);

  }

  private static void processEvents(Flux<Integer> flux, int key) {
    flux.subscribe(i -> System.out.println("Key: " + key + ", Item: " + i));
  }
}
