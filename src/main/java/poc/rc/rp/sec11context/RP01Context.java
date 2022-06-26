package poc.rc.rp.sec11context;

import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class RP01Context {

  /**
   * context is set by downstream can be used above
   * contexts are thread safe by default and immutable
   */
  public static void main(String[] args) {
    welcomeMessage()
        .contextWrite(ctx -> ctx.put("user", ctx.get("user").toString().toUpperCase())) // to update
        .contextWrite(Context.of("user", "sam")) // to set context
        .subscribe(Util.getSubscriber());
  }

  private static Mono<String> welcomeMessage() {
//    return Mono.fromSupplier(() -> "Welcome!");
    return Mono.deferContextual(ctx -> {
      if (ctx.hasKey("user")) {
        return Mono.just("Welcome " + ctx.get("user"));
      }
      return Mono.error(new RuntimeException("Not Authenticated!"));
    });
  }
}
