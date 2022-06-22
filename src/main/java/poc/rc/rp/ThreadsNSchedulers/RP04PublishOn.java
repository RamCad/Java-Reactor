package poc.rc.rp.ThreadsNSchedulers;

import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class RP04PublishOn {
  public static void main(String[] args) {

    Flux<Object> flux = Flux.create(fluxSink -> {
      printCurrThread("create");
      for (int i = 0; i < 4; i++) {
        fluxSink.next(i);
      }
      fluxSink.complete();
    })
        .doOnNext(i -> printCurrThread("next: " + i));

    flux
        .publishOn(Schedulers.boundedElastic())
        .doOnNext(i -> printCurrThread("next: " + i))
        .publishOn(Schedulers.parallel())
        .subscribe(v -> printCurrThread("subscriber1: " + v));

    Util.sleepSeconds(5);

  }

  private static void printCurrThread(String msg) {
    System.out.println(msg + "\t\t Thread: " + Thread.currentThread().getName());
  }
}
