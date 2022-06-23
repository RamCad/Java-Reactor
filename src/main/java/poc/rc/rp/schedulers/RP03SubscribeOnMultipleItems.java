package poc.rc.rp.schedulers;

import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class RP03SubscribeOnMultipleItems {

  public static void main(String[] args) {

    Flux<Object> flux = Flux.create(fluxSink -> {
      printCurrThread("create");
      for (int i = 0; i < 20; i++) {
        fluxSink.next(i);
      }
      fluxSink.complete();
    })
        .doOnNext(i -> printCurrThread("next: " + i));

    flux
        .subscribeOn(Schedulers.boundedElastic())
        .subscribe(v -> printCurrThread("subscriber1: " + v));

    flux
        .subscribeOn(Schedulers.parallel())
        .subscribe(v -> printCurrThread("subscriber2: " + v));

    Util.sleepSeconds(5);

  }

  private static void printCurrThread(String msg) {
    System.out.println(msg + "\t\t Thread: " + Thread.currentThread().getName());
  }
}
