package poc.rc.rp.operators;

import java.time.Duration;
import poc.rc.rp.Util;
import reactor.core.publisher.Flux;

public class RP04Delay {

  public static void main(String[] args) {

    /**
     * see Queues.class - looks for system property else default value is 32
     * System.setProperty("reactor.bufferSize.x", "10");
     */
    Flux.range(1, 100) // request for 32
        .log()
        .delayElements(Duration.ofSeconds(1)) // internal limit rate
        .subscribe(Util.getSubscriber());

    Util.sleepSeconds(60);
  }
}
