package poc.rc.rp.sec11context.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class BookService {

  private static Map<String, Integer> map = new HashMap<>();

  static {
    map.put("std", 2);
    map.put("prime", 3);
  }

  public static Mono<String> getBook() {
    return Mono.deferContextual(ctx -> {
      if(ctx.get("allow")){
        return Mono.just(Util.faker().book().title());
      }
      return Mono.error(new RuntimeException("Not Allowed!"));
    })
        .contextWrite(rateLimiter());
  }

  private static Function<Context, Context> rateLimiter() {
    return ctx -> {
      if(ctx.hasKey("category")) {
        String category = ctx.get("category").toString();
        Integer attempts = map.get(category);
        if(attempts > 0) {
          map.put(category, attempts - 1);
          return ctx.put("allow", true);
        }
      }
      return ctx.put("allow", false);
    };
  }
}
