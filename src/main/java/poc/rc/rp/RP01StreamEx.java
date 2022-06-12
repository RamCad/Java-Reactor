package poc.rc.rp;

import java.util.stream.Stream;

public class RP01StreamEx {

  public static void main(String[] args) {
    Stream<Integer> integerStream = Stream.of(1)
        .map(i -> {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException ie) {
            ie.printStackTrace();
          }
          return i * 2;
        });

    //Stream is lazy - doesn't do anything until there is a terminal operator
//    System.out.println(integerStream);

    integerStream.forEach(System.out::println);
  }
}