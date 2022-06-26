package poc.rc.rp.sec04hotncoldpublishers;

import java.time.Duration;
import java.util.stream.Stream;
import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;

public class RP04HotPublisherAutoConnect {
  public static void main(String[] args) {
    Flux<String> stringFlux = Flux.fromStream(() -> getContent())
        .delayElements(Duration.ofSeconds(2))
        .publish()
        .autoConnect(1); // doesn't let to reconnect
    //minSubscribers can be 0
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
