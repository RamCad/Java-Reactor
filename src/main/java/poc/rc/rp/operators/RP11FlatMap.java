package poc.rc.rp.operators;

import poc.rc.rp.OrderService;
import poc.rc.rp.UserService;
import poc.rc.rp.Util;

public class RP11FlatMap {

  public static void main(String[] args) {
    UserService.getUsers()
        .flatMap(user -> OrderService.getOrders(user.getUserId()))
        .subscribe(Util.getSubscriber());
  }
}
