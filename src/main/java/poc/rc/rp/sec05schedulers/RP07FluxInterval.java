package poc.rc.rp.sec05schedulers;

import java.time.Duration;
import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;

public class RP07FluxInterval {

  public static void main(String[] args) {
    /**
     * why does this exit immediately?
     * interval() internally uses Schedulers.parallel()
     */
    Flux.interval(Duration.ofSeconds(1))
        .subscribe(Util.getSubscriber());
  }
}
