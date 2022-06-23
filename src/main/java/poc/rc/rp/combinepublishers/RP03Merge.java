package poc.rc.rp.combinepublishers;

import poc.rc.rp.combinepublishers.helpers.AmericanAirlines;
import poc.rc.rp.combinepublishers.helpers.EmiratesFlights;
import poc.rc.rp.combinepublishers.helpers.QatarFlights;
import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;

public class RP03Merge {

  public static void main(String[] args) {
    Flux<String> flux = Flux.merge(QatarFlights.getFlights(), EmiratesFlights.getFlights(),
        AmericanAirlines.getFlights());

    flux.subscribe(Util.getSubscriber());

    Util.sleepSeconds(5);
  }
}
