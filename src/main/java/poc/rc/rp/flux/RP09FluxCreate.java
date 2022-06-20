package poc.rc.rp.flux;

import poc.rc.rp.commonutils.NameProducer;
import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;

public class RP09FluxCreate {

  public static void main(String[] args) {
//    Flux.create(fluxSink -> {
//      fluxSink.next(1);
//      fluxSink.next(2);
//      fluxSink.next(3);
//      fluxSink.complete();
//    }).subscribe(Util.getSubscriber());

//    Flux.create(fluxSink -> {
//      for (int i = 0; i < 10; i++) {
//        fluxSink.next(Util.faker().country().name());
//      }
//      fluxSink.complete();
//    }).subscribeWith(Util.getSubscriber());

    NameProducer nameProducer = new NameProducer();
    Flux.create(nameProducer)
        .subscribeWith(Util.getSubscriber());

    nameProducer.produce();

    /**
     * FluxSink - thread safe - can be shared with multiple threads
     * emit items from multiple threads
     */

    Runnable runnable = nameProducer::produce;

    for (int i = 0; i < 10; i++) {
      new Thread(runnable).start();
    }

    Util.sleepSeconds(3);
  }
}
