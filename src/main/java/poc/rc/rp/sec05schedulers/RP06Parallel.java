package poc.rc.rp.sec05schedulers;

import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class RP06Parallel {
  public static void main(String[] args) {

    /**
     * parallel returns parallel Flux so we can't use publishOn and subscribeOn
     * use sequential()
     */
    Flux.range(1, 10)
        .parallel(2) // can use default parallel()
        .runOn(Schedulers.parallel())
        .doOnNext(i -> printCurrThread("next: " + i))
        .sequential()
        .subscribe(v -> printCurrThread("subscriber1: " + v));

    Util.sleepSeconds(5);

  }

  private static void printCurrThread(String msg) {
    System.out.println(msg + "\t\t Thread: " + Thread.currentThread().getName());
  }
}
