package poc.rc.rp.commonutils;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Person {

  private String name;
  private int age;

  public Person() {
    this.name = Util.faker().name().fullName();
    this.age = Util.faker().random().nextInt(1, 10);
  }
}
