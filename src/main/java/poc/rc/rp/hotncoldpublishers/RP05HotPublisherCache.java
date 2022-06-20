package poc.rc.rp.hotncoldpublishers;

import java.time.Duration;
import java.util.stream.Stream;
import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;

public class RP05HotPublisherCache {
  public static void main(String[] args) {
    Flux<String> stringFlux = Flux.fromStream(() -> getContent())
        .delayElements(Duration.ofSeconds(2))
        .cache(); // cache() = publish().replay(); stores upto Integer.MAX_VALUE
    stringFlux
        .subscribe(Util.getSubscriber("Sub1"));
    Util.sleepSeconds(5);
    stringFlux
        .subscribe(Util.getSubscriber("Sub2"));
    Util.sleepSeconds(60);
  }

  // Streaming Service - Ex: Netflix
  private static Stream<String> getContent() {
    System.out.println("Content streaming");
    return Stream.of(
        "Content1", "Content2", "Content3", "Content4", "Content5"
    );
  }
}
