package poc.rc.rp.operators;

import poc.rc.rp.OrderService;
import poc.rc.rp.UserService;
import poc.rc.rp.Util;

public class RP12ConcatMap {

  public static void main(String[] args) {
    UserService.getUsers()
        .concatMap(user -> OrderService.getOrders(user.getUserId()))
        .subscribe(Util.getSubscriber());
  }
}
