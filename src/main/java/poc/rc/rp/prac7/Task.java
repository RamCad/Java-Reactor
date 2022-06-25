package poc.rc.rp.prac7;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;

public class Task {

  public static void main(String[] args) {
    Map<String, Function<Flux<PurchaseOrder>, Flux<PurchaseOrder>>> map = Map.of(
        "Kids", OrderProcessor.kidsProcessing(),
        "Automotive", OrderProcessor.automotiveProcessing()
    );
    Set<String> keys = map.keySet();

    OrderService.orders()
        .filter(p -> keys.contains(p.getCategory()))
        .groupBy(PurchaseOrder::getCategory)
        .flatMap(groupedFlux -> map.get(groupedFlux.key()).apply(groupedFlux))
        .subscribe(Util.getSubscriber());

    Util.sleepSeconds(60);
  }
}
