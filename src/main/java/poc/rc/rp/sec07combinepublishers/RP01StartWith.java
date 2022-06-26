package poc.rc.rp.sec07combinepublishers;

import poc.rc.rp.commonutils.NameGenerator;
import poc.rc.rp.commonutils.Util;

public class RP01StartWith {

  public static void main(String[] args) {
    NameGenerator generator = new NameGenerator();
    generator.generateNames()
        .take(2)
        .subscribe(Util.getSubscriber("Sub 1: "));
    generator.generateNames()
        .take(2)
        .subscribe(Util.getSubscriber("Sub 2: "));
    generator.generateNames()
        .take(3)
        .subscribe(Util.getSubscriber("Sub 3: "));
    generator.generateNames()
        .filter(n -> n.startsWith("A"))
        .take(1)
        .subscribe(Util.getSubscriber("Sub 3: "));
  }
}
