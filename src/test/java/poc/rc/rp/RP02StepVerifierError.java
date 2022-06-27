package poc.rc.rp;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class RP02StepVerifierError {

  @Test
  public void test() {
    Flux<Integer> just = Flux.just(1, 2, 3);
    Flux<Integer> error = Flux.error(new RuntimeException("error"));

    Flux<Integer> flux = just.concatWith(error);

    StepVerifier.create(flux)
        .expectNext(1, 2, 3)
        .verifyError();
  }

  @Test
  public void test1() {
    Flux<Integer> just = Flux.just(1, 2, 3);
    Flux<Integer> error = Flux.error(new RuntimeException("error"));

    Flux<Integer> flux = Flux.concat(just, error);

    StepVerifier.create(flux)
        .expectNext(1, 2, 3)
        .verifyError(RuntimeException.class); // verifyErrorMessage("error");
  }
}
