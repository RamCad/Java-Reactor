package poc.rc.rp.commonutils;

import java.util.ArrayList;
import java.util.List;
import reactor.core.publisher.Flux;

public class NameGenerator {

  private List<String> list = new ArrayList<>();

  public Flux<String> generateNames() {
    return Flux.generate(stringSynchronousSink -> {
      System.out.println("--Generating name--");
      String name = Util.faker().name().fullName();
      list.add(name);
      stringSynchronousSink.next(name);
    })
        .cast(String.class)
        .startWith(getFromCache());
  }

  private Flux<String> getFromCache() {
    return Flux.fromIterable(list);
  }
}
