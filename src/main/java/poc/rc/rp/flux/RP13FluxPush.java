package poc.rc.rp.flux;

import poc.rc.rp.commonutils.NameProducer;
import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;

public class RP13FluxPush {

  public static void main(String[] args) {
    /**
     * create - thread-safe, uses serialized sink
     * push - not thread-safe, single thread producer
     */
    NameProducer nameProducer = new NameProducer();

    Flux.push(nameProducer)
        .subscribe(Util.getSubscriber());

    Runnable runnable = nameProducer::produce;

    for (int i = 0; i < 10; i++) {
      new Thread(runnable).start();
    }
  }
}
