package poc.rc.rp.sec03operators;

import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;

public class RP02DoCallBacks {

  public static void main(String[] args) {
    Flux.create(fluxSink -> {
      System.out.println("--Flux Create--");
      for (int i = 0; i < 5; i++) {
        fluxSink.next(i);
      }
      fluxSink.complete();
      System.out.println("--Flux Create Complete--");
    })
        .doOnComplete(() -> System.out.println("--doOnComplete--"))
        .doFirst(() -> System.out.println("--doFirst last--"))
        .doOnNext(o -> System.out.println("doOnNext : " + o))
        .doOnSubscribe(subscription -> System.out.println("doOnSubscribe: 2 " + subscription))
        .doOnRequest(value -> System.out.println("doOnRequest: " + value))
        .doOnError(throwable -> System.out.println("doOnError: " + throwable.getMessage()))
        .doOnTerminate(() -> System.out.println("--doOnTerminate--"))
        .doOnCancel(() -> System.out.println("--doOnCancel--"))
        .doFinally(signalType -> System.out.println("doFinally: " + signalType))
        .doOnDiscard(Object.class, o -> System.out.println("doOnDiscard: " + o))
        .take(2)

        .doFirst(() -> System.out.println("--doFirst first--")) // executed very first as request goes from bottom
        .doOnSubscribe(subscription -> System.out.println("doOnSubscribe: 1 " + subscription)) // executed after doOnSubscribe 2 as response is sent from publisher
        .subscribe(Util.getSubscriber());
  }
}
