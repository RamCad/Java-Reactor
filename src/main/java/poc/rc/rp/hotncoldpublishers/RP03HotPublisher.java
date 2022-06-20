package poc.rc.rp.hotncoldpublishers;

import java.time.Duration;
import java.util.stream.Stream;
import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;

public class RP03HotPublisher {

  public static void main(String[] args) {
    // share() = publish() + refCount(1)
    Flux<String> stringFlux = Flux.fromStream(() -> getContent())
        .delayElements(Duration.ofSeconds(2))
        .publish() // .publish() - returns ConnectableFlux
        .refCount(1);
    stringFlux
        .subscribe(Util.getSubscriber("Sub1"));
    Util.sleepSeconds(5);
    stringFlux
        .subscribe(Util.getSubscriber("Sub2"));
    Util.sleepSeconds(60);
  }

  // Streaming Service
  private static Stream<String> getContent() {
    System.out.println("Content streaming");
    return Stream.of(
        "Content1", "Content2", "Content3", "Content4", "Content5"
    );
  }
}
