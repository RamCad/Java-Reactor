package poc.rc.rp.prac7;

import java.time.Duration;
import reactor.core.publisher.Flux;

public class OrderService {

  public static Flux<PurchaseOrder> orders() {
    return Flux.interval(Duration.ofMillis(100))
        .map(i -> new PurchaseOrder());
  }
}
