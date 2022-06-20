package poc.rc.rp.prac4;

import poc.rc.rp.commonutils.Util;

public class Practice4 {

  public static void main(String[] args) {
    OrderService orderService = new OrderService();
    RevenueService revenueService = new RevenueService();
    InventoryService inventoryService = new InventoryService();

    // revenue and inventory - observe order stream
    orderService.orderStream().subscribe(revenueService.subscribeAutoStream());
    orderService.orderStream().subscribe(inventoryService.subscribeAutoStream());

    inventoryService.inventorStream()
        .subscribe(Util.getSubscriber("inventory"));

    revenueService.revenueStream()
        .subscribe(Util.getSubscriber("revenue"));

    Util.sleepSeconds(60);
  }
}
