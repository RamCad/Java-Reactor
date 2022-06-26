package poc.rc.rp.sec07combinepublishers.helpers;

import java.time.Duration;
import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;

public class EmiratesFlights {
  public static Flux<String> getFlights() {
    return Flux.range(1, Util.faker().random().nextInt(1, 10))
        .delayElements(Duration.ofSeconds(1))
        .map(i -> "Emirates " + Util.faker().random().nextInt(100, 999))
        .filter(i -> Util.faker().random().nextBoolean());
  }
}
