package poc.rc.rp;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class RP05SupplierRefactoring {

  public static void main(String[] args) {

    getName();
    getName()
        .subscribeOn(Schedulers.boundedElastic()) //async
        .subscribe(Util.onNext());
    String name = getName()
        .subscribeOn(Schedulers.boundedElastic()) //async
        .block(); // block should not be used, as it will block the main thread
    System.out.println(name);
    getName();

    Util.sleepSeconds(4);
  }

  private static Mono<String> getName() {
    System.out.println("Entry: getName()");
    return Mono.fromSupplier(() -> {
      System.out.println("Execute: Supplier");
      Util.sleepSeconds(2);
      return Util.faker().name().name();
    }).map(String::toUpperCase);
  }
}
