package poc.rc.rp.sec01mono;

import java.util.concurrent.Callable;
import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Mono;

public class RP04MonoFromSupplier {

  public static void main(String[] args) {
    // use just only when data is available
//    Mono.just(getName()); //method will be invoked even without any consumer

    //lazy loading - supplier is best choice
//    Supplier<String> strSupplier = () -> getName();
    Mono<String> mono = Mono.fromSupplier(() -> getName());
    mono.subscribe(Util.onNext());

    Callable<String> callable = () -> getName();
    Mono<String> monoCallable = Mono.fromCallable(callable);
    monoCallable.subscribe(Util.onNext());
  }

  private static String getName() {
    System.out.println("Entry: getName()");
    return Util.faker().name().name();
  }

}
