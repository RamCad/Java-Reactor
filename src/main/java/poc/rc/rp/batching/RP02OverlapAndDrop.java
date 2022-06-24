package poc.rc.rp.batching;

import java.time.Duration;
import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;

public class RP02OverlapAndDrop {
  public static void main(String[] args) {
    publishEvents()
        .buffer(3, 1)
        .subscribe(Util.getSubscriber());
    Util.sleepSeconds(20);
  }

  private static Flux<String> publishEvents() {
    return Flux.interval(Duration.ofMillis(300))
        .map(i -> "event " + i);
  }
}
