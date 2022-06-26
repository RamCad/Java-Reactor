package poc.rc.rp.sec11context;

import poc.rc.rp.commonutils.Util;
import poc.rc.rp.sec11context.helper.BookService;
import poc.rc.rp.sec11context.helper.UserService;
import reactor.util.context.Context;

public class RP02CtxRateLimiterDemo {

  public static void main(String[] args) {
    BookService.getBook()
        .contextWrite(UserService.userCategory())
        .contextWrite(Context.of("user", "sam"))
        .subscribe(Util.getSubscriber());

    BookService.getBook()
        .repeat(2)
        .contextWrite(UserService.userCategory())
        .contextWrite(Context.of("user", "sam"))
        .subscribe(Util.getSubscriber());

    BookService.getBook()
        .repeat(2)
        .contextWrite(UserService.userCategory())
        .contextWrite(Context.of("user", "mike"))
        .subscribe(Util.getSubscriber());
  }
}
