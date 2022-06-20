package poc.rc.rp.operators;

import poc.rc.rp.Util;
import reactor.core.publisher.Flux;

public class RP03LimitRate {

  public static void main(String[] args) {
    Flux.range(1, 1000)
        .log()
        .limitRate(100) // Once 75% data is consumed another request will be sent
//        .limitRate(100, 0) // after 100% data consumption
//        .limitRate(100, 100) // 75%
        .subscribe(Util.getSubscriber());
  }
}
