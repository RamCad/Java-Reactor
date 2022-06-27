package poc.rc.rp;

import java.time.Duration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import poc.rc.rp.prac6.BookOrder;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class RP04AssertTest {

  @Test
  public void test() {
    Mono<BookOrder> bookOrderMono = Mono.fromSupplier(() -> new BookOrder());

    StepVerifier.create(bookOrderMono)
        .assertNext(b -> Assertions.assertNotNull(b.getAuthor()))
        .verifyComplete();
  }

  @Test
  public void test1() {
    Mono<BookOrder> bookOrderMono = Mono.fromSupplier(() -> new BookOrder())
        .delayElement(Duration.ofSeconds(3));

    StepVerifier.create(bookOrderMono)
        .assertNext(b -> Assertions.assertNotNull(b.getAuthor()))
        .expectComplete()
//        .verify(Duration.ofSeconds(2)); // will fail
        .verify(Duration.ofSeconds(4));
  }
}
