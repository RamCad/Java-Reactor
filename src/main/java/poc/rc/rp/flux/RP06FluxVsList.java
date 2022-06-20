package poc.rc.rp.flux;

import java.time.LocalTime;
import java.util.List;
import poc.rc.rp.commonutils.Util;

public class RP06FluxVsList {

  public static void main(String[] args) {
    System.out.println(LocalTime.now());
    List<String> names = Helper.getNames(5);
    System.out.println(names);
    System.out.println(LocalTime.now());

    Helper.getFluxNames(5)
        .subscribe(Util.onNext());
    System.out.println(LocalTime.now());
  }
}
