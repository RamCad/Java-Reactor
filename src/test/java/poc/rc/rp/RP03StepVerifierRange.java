package poc.rc.rp;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class RP03StepVerifierRange {
  @Test
  public void test() {
    Flux<Integer> range = Flux.range(1, 50);

    StepVerifier.create(range)
        .expectNextCount(50)
        .verifyComplete();
  }

  @Test
  public void test1() {
    Flux<Integer> range = Flux.range(1, 50);

    StepVerifier.create(range)
        .thenConsumeWhile(i -> i < 100)
        .verifyComplete();
  }

}