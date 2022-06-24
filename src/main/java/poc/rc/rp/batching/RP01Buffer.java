package poc.rc.rp.batching;

import java.time.Duration;
import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;

public class RP01Buffer {

  public static void main(String[] args) {
    publishEvents()
//        .buffer(5) // If there is delay in publishing buffer waits for 5 items before giving to subscriber
//        .buffer(Duration.ofSeconds(2)) // process items based on time - sometimes we may have too many items
        .bufferTimeout(5, Duration.ofSeconds(2))
        .subscribe(Util.getSubscriber());
    Util.sleepSeconds(20);
  }

  private static Flux<String> publishEvents() {
    return Flux.interval(Duration.ofMillis(300))
        .take(3)
        .map(i -> "event " + i);
  }
}
