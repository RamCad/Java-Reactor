package poc.rc.rp;

import java.time.Duration;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class RP05VirtualTimeTest {

  @Test
  public void test() {
    /*
    StepVerifier.create(timeConsuming())
        .expectNext("1a", "2a", "3a", "4a") // takes a lot time
        .verifyComplete();
        */

    StepVerifier.withVirtualTime(() -> timeConsuming())
        .thenAwait(Duration.ofSeconds(30))
        .expectNext("1a", "2a", "3a")
        .verifyComplete();
  }

  @Test
  public void test1() {

    StepVerifier.withVirtualTime(() -> timeConsuming())
        .expectSubscription() // sub is an event
        .expectNoEvent(Duration.ofSeconds(4))
        .thenAwait(Duration.ofSeconds(20))
        .expectNext("1a", "2a", "3a")
        .verifyComplete();
  }

  private Flux<String> timeConsuming() {
    return Flux.range(1, 4)
        .delayElements(Duration.ofSeconds(5))
        .map(i -> i + "a");
  }
}
