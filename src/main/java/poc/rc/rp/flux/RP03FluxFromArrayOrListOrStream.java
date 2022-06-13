package poc.rc.rp.flux;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import poc.rc.rp.Util;
import reactor.core.publisher.Flux;

public class RP03FluxFromArrayOrListOrStream {

  public static void main(String[] args) {
    List<String> strings = Arrays.asList("a", "b", "c");

    //Similar to .just
    Flux.fromIterable(strings)
        .subscribe(Util.onNext());

    Integer[] arr = { 2, 6, 3, 9 };

    Flux.fromArray(arr)
        .subscribe(Util.onNext());

    //Stream is one time use
    Stream<String> stream = strings.stream();

    stream.forEach(System.out::println);

    // error - as the stream is already closed
    Flux.fromStream(stream)
        .subscribe(Util.onNext(), Util.onError(), Util.onComplete());

    // To overcome above scenario
    Flux.fromStream(() -> strings.stream())
        .subscribe(Util.onNext());

    Flux.fromStream(() -> strings.stream())
        .subscribe(Util.onNext());

  }

}
