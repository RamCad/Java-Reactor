package poc.rc.rp.sinks;

import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.One;

public class RP01SinkOne {

  /**
   * Emit signals manually not using range, interval
   * Sinks - both publisher and subscriber
   * better alternative to processor
   *
   * Sinks created from factory methods
   */

  public static void main(String[] args) {
    // Mono 1 value or empty or error
    // publish from here
    One<Object> sink = Sinks.one();

    // subscribe from here
    Mono<Object> mono = sink.asMono();

    mono.subscribe(Util.getSubscriber("sub 1"));
    mono.subscribe(Util.getSubscriber("sub 2")); // multiple subscribers

    sink.tryEmitValue("test sink");

    sink.emitValue("test", (signalType, emitResult) -> {
      System.out.println("SignalType: " + signalType.name());
      System.out.println("EmitResult: " + emitResult.name());
      return false; // retry or not
    });
  }
}
