package poc.rc.rp.sec02flux;

import reactor.core.publisher.Flux;

public class RP02MultipleSubscribers {

  public static void main(String[] args) {
    Flux<Integer> integerFlux = Flux.just(1, 2, 3, 4);

    integerFlux.subscribe(i -> System.out.println("Sub 1: " + i));
    integerFlux.subscribe(i -> System.out.println("Sub 2: " + i));
    integerFlux.subscribe(i -> System.out.println("Sub 3: " + i));

  }

}
