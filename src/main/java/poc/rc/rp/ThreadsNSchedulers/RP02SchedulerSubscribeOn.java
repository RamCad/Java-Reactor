package poc.rc.rp.ThreadsNSchedulers;

import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class RP02SchedulerSubscribeOn {
  public static void main(String[] args) {

    Flux<Object> flux = Flux.create(fluxSink -> {
      printCurrThread("create");
      fluxSink.next(1);
    })
        .subscribeOn(Schedulers.newParallel("rc"))
        .doOnNext(i -> printCurrThread("next: " + i));

    /**
     * Schedulers.parallel()        4 core CPU - 4 threads (1 thread/CPU)
     * Schedulers.boundedElastic()  4 core CPU - 40 threads (10 threads/CPU)
     *
     * when there are multiple subscribeOn(Schedulers) - closest one to the publisher will be taken
     */

    flux
        .doFirst(() -> printCurrThread("first2"))
        .subscribeOn(Schedulers.boundedElastic())
        .doFirst(() -> printCurrThread("first1"))
        .subscribe(v -> printCurrThread("subscriber: " + v));

    Util.sleepSeconds(5);

  }

  private static void printCurrThread(String msg) {
    System.out.println(msg + "\t\t Thread: " + Thread.currentThread().getName());
  }
}
