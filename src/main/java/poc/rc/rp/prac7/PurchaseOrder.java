package poc.rc.rp.prac7;

import lombok.Data;
import lombok.ToString;
import poc.rc.rp.commonutils.Util;

@Data
@ToString
public class PurchaseOrder {

  private String item;
  private double price;
  private String category;

  public PurchaseOrder() {
    this.item = Util.faker().commerce().productName();
    this.price = Double.parseDouble(Util.faker().commerce().price());
    this.category = Util.faker().commerce().department();
  }
}
