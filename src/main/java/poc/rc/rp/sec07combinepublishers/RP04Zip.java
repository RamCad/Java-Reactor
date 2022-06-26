package poc.rc.rp.sec07combinepublishers;

import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;

public class RP04Zip {

  public static void main(String[] args) {
    Flux.zip(getBody(), getTyres(), getEngine())
        .subscribe(Util.getSubscriber());
  }

  private static Flux<String> getBody() {
    return Flux.range(1, 5)
        .map(i -> "body");
  }

  private static Flux<String> getEngine() {
    return Flux.range(1, 2)
        .map(i -> "engine");
  }

  private static Flux<String> getTyres() {
    return Flux.range(1, 6)
        .map(i -> "tyres");
  }
}
