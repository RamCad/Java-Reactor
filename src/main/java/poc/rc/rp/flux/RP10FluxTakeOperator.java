package poc.rc.rp.flux;

import poc.rc.rp.Util;
import reactor.core.publisher.Flux;

public class RP10FluxTakeOperator {

  public static void main(String[] args) {
    Flux.range(1, 10)
        .log()
        .take(3) // cancels subscription after 3
        .log()
        .subscribe(Util.getSubscriber());
  }
}
