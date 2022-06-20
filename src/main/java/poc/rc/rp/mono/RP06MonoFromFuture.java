package poc.rc.rp.mono;

import java.util.concurrent.CompletableFuture;
import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Mono;

public class RP06MonoFromFuture {

  public static void main(String[] args) {
    Mono.fromFuture(getName())
        .subscribe(Util.onNext());

    Util.sleepSeconds(1);
  }

  private static CompletableFuture<String> getName() {
    return CompletableFuture.supplyAsync(() -> Util.faker().name().fullName());
  }
}
