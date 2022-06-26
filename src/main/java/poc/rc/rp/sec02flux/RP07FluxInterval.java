package poc.rc.rp.sec02flux;

import java.time.Duration;
import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;

public class RP07FluxInterval {

  public static void main(String[] args) {
    Flux.interval(Duration.ofSeconds(1))
        .subscribe(Util.onNext());
  }
}
