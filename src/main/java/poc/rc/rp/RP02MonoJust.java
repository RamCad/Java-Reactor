package poc.rc.rp;

import reactor.core.publisher.Mono;

public class RP02MonoJust {

  public static void main(String[] args) {
    //publisher
    Mono<Integer> integerMono = Mono.just(1);

    //Nothing happens until you subscribe
//    System.out.println(integerMono);

    integerMono.subscribe(i -> System.out.println("Emit: " + i));
  }
}
