package poc.rc.rp.prac6;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;

public class BookRevenueByCategory {

  public static void main(String[] args) {
    Set<String> categories = Set.of(
        "Science fiction", "Fantasy", "Suspense/Thriller"
    );
    books()
        .filter(book -> categories.contains(book.getCategory()))
        .buffer(Duration.ofSeconds(5))
        .map(BookRevenueByCategory::calc)
        .subscribe(Util.getSubscriber());

    Util.sleepSeconds(40);
  }

  private static RevenueReport calc(List<BookOrder> books) {
    Map<String, Double> revenue = books.stream()
        .collect(Collectors.groupingBy(
            BookOrder::getCategory,
            Collectors.summingDouble(BookOrder::getPrice)));
    return new RevenueReport(revenue);
  }

  private static Flux<BookOrder> books() {
    return Flux.interval(Duration.ofMillis(200))
        .map(i -> new BookOrder());
  }
}
