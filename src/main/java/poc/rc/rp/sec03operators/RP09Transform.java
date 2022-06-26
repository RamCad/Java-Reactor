package poc.rc.rp.sec03operators;

import java.util.function.Function;
import poc.rc.rp.commonutils.Person;
import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;

public class RP09Transform {

  public static void main(String[] args) {
    getPerson()
        .transform(applyFilterMap())
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
