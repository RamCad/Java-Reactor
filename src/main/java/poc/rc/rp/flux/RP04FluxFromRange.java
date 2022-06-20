package poc.rc.rp.flux;

import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;

public class RP04FluxFromRange {

  public static void main(String[] args) {
    Flux.range(0, 5)
        .log()
        .subscribe(Util.onNext());
  }
}
