package poc.rc.rp;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

public class RP06ScenarioName {
  /**
   * To get additional information
   */
  @Test
  public void test() {
    Flux<String> flux = Flux.just("a", "b", "c");

    StepVerifierOptions scenarioName = StepVerifierOptions.create()
        .scenarioName("Alphabets-test");

    StepVerifier.create(flux, scenarioName)
        .expectNextCount(3)
        .verifyComplete();
  }

  @Test
  public void test1() {
    Flux<String> flux = Flux.just("a", "b1", "c");


    StepVerifier.create(flux)
        .expectNext("a")
        .as("a-test")
        .expectNext("b")
        .as("b-test")
        .expectNext("c")
        .as("c-test")
        .verifyComplete();
  }
}
