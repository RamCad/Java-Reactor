package poc.rc.rp;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;
import reactor.util.context.Context;

public class RP07Context {

  @Test
  public void test() {
    StepVerifier.create(welcomeMessage())
        .verifyError(RuntimeException.class);
  }

  @Test
  public void test1() {
    StepVerifierOptions initialContext = StepVerifierOptions.create()
        .withInitialContext(Context.of("user", "sam"));
    StepVerifier.create(welcomeMessage(), initialContext)
        .expectNext("Welcome sam")
        .verifyComplete();
  }

  private Mono<String> welcomeMessage() {
    return Mono.deferContextual(ctx -> {
      if (ctx.hasKey("user")) {
        return Mono.just("Welcome " + ctx.get("user"));
      }
      return Mono.error(new RuntimeException("Not Authenticated!"));
    });
  }
}
