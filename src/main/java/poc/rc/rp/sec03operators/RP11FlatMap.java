package poc.rc.rp.sec03operators;

import poc.rc.rp.commonutils.OrderService;
import poc.rc.rp.commonutils.UserService;
import poc.rc.rp.commonutils.Util;

public class RP11FlatMap {

  public static void main(String[] args) {
    UserService.getUsers()
        .flatMap(user -> OrderService.getOrders(user.getUserId()))
        .subscribe(Util.getSubscriber());
  }
}
