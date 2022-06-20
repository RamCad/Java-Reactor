package poc.rc.rp.operators;

import java.util.function.Function;
import poc.rc.rp.Person;
import poc.rc.rp.Util;
import reactor.core.publisher.Flux;

public class RP10SwitchOnFirst {

  public static void main(String[] args) {
    /**
     * verifies whether emitted data is in expected format
     * decision is taken based on 1st item
     */
    getPerson()
        .switchOnFirst(((signal, personFlux) ->
            signal.isOnNext() && signal.get().getAge() > 10
            ? personFlux
            : applyFilterMap().apply(personFlux)))
//        .transform(applyFilterMap())
        .subscribe(Util.getSubscriber());
  }

  public static Flux<Person> getPerson() {
    return Flux.range(1, 10)
        .map(i -> new Person());
  }

  public static Function<Flux<Person>, Flux<Person>> applyFilterMap() {
    return flux -> flux.filter(p -> p.getAge() > 10)
        .doOnNext(p -> p.setName(p.getName().toUpperCase()))
        .doOnDiscard(Person.class, p -> System.out.println("Discarded: " + p));
  }
}
