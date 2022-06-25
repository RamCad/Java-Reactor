package poc.rc.rp.sinks;

import java.time.Duration;
import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.Many;

public class RP04SinkMulticast {
//  public static void main(String[] args) {
//    // handle through which we emit items
////    Many<Object> sinkMulticast = Sinks.many().multicast().onBackpressureBuffer();
//
//    Many<Object> sinkMulticast = Sinks.many().multicast().directAllOrNothing(); // to disable history
//
//    // subscribe from here
//    Flux<Object> flux = sinkMulticast.asFlux();
//
//    sinkMulticast.tryEmitNext("test 1 sink multicast");
//    sinkMulticast.tryEmitNext("test 2 sink multicast");
//
//    flux.subscribe(Util.getSubscriber("sub 1")); // will receive all 3 messages - as there were no subscribers before
//    flux.subscribe(Util.getSubscriber("sub 2")); // will receive last msg only as only 1 item was emitted after subscription
//
//    sinkMulticast.tryEmitNext("after sub 2");
//    sinkMulticast.tryEmitComplete();
//
//  }

  /**
   * Example - direct best effort
   */

  public static void main(String[] args) {

    System.setProperty("reactor.bufferSize.small", "16");

    // Either everyone gets a value or no one gets
    Many<Object> sinkMulticast = Sinks.many().multicast().directAllOrNothing();

    // use directBestEffort() if above is not suitable

    Flux<Object> flux = sinkMulticast.asFlux();

    flux.subscribe(Util.getSubscriber("sub 1"));
    flux.delayElements(Duration.ofMillis(200)).subscribe(Util.getSubscriber("sub 2"));

    for (int i = 0; i < 100; i++) {
      sinkMulticast.tryEmitNext(i);
    }

    Util.sleepSeconds(10);

  }
}
