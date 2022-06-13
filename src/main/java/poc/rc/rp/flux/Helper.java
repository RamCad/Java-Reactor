package poc.rc.rp.flux;

import java.util.ArrayList;
import java.util.List;
import poc.rc.rp.Util;
import reactor.core.publisher.Flux;

public class Helper {
  public static List<String> getNames(int count) {
    List<String> names = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      names.add(getName());
    }
    return names;
  }

  public static Flux<String> getFluxNames(int count) {
   return Flux.range(0, count)
       .map(i -> getName());
  }

  private static String getName() {
    Util.sleepSeconds(1);
    return Util.faker().name().fullName();
  }
}
