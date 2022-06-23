package poc.rc.rp.prac4;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import reactor.core.publisher.Flux;

public class InventoryService {
  private Map<String, Integer> db = new HashMap<>();

  public InventoryService() {
    db.put("Kids", 100);
    db.put("Automotive", 100);
  }

  public Consumer<PurchaseOrder> subscribeAutoStream() {
    return p -> db.computeIfPresent(p.getCategory(), (k, v) -> v - p.getQuantity());
  }

  public Flux<String> inventorStream() {
    return Flux.interval(Duration.ofSeconds(2))
        .map(i -> db.toString());
  }
}