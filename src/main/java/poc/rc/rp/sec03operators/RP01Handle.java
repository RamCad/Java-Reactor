package poc.rc.rp.sec03operators;

import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;

public class RP01Handle {

  public static void main(String[] args) {
    /**
     * handle = filter + map
     */
    Flux.range(1, 20)
        .handle((integer, synchronousSink) -> {
          synchronousSink.next(integer);
        })
        .subscribe(Util.getSubscriber());
    /**
     * Example
     */
    Flux.generate(synchronousSink ->
        synchronousSink.next(Util.faker().country().name()))
        .map(Object::toString)
        .handle((s, synchronousSink) -> {
          synchronousSink.next(s);
          if (s.equalsIgnoreCase("canada")) {
            synchronousSink.complete();
          }
        })
        .subscribe(Util.getSubscriber());
  }
}
