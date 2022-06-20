package poc.rc.rp.commonutils;

import com.github.javafaker.Faker;
import java.util.function.Consumer;
import org.reactivestreams.Subscriber;

public class Util {

  private static final Faker FAKER = Faker.instance();

  public static Consumer<Object> onNext() {
    return obj -> System.out.println("onNext: " + obj);
  }
  public static Consumer<Throwable> onError() {
    return e -> System.out.println("onError: " + e.getMessage());
  }
  public static Runnable onComplete() {
    return () -> System.out.println("complete");
  }
  public static Faker faker() {
    return FAKER;
  }
  public static void sleepSeconds(int seconds) {
    try {
      Thread.sleep(seconds * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static Subscriber<Object> getSubscriber() {
    return new DefaultSubscriber();
  }

  public static Subscriber<Object> getSubscriber(String name) {
    return new DefaultSubscriber(name);
  }
}
