package poc.rc.rp;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class RP01StepVerifierDemo {

  @Test
  public void test() {
    Flux<Integer> flux = Flux.just(1, 2, 3);

    StepVerifier.create(flux)
//        .expectNextCount(3)
        .expectNext(1)
        .expectNext(2)
        .expectNext(3) // expectNext(1, 2, 3);
        .verifyComplete(); // expectComplete().verify();
  }
}
