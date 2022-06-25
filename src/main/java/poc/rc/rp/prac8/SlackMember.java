package poc.rc.rp.prac8;

import java.util.function.Consumer;

public class SlackMember {

  private String name;
  private Consumer<String> messageConsumer;

  public SlackMember(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void receives(String message) {
    System.out.println(message);
  }

  public void says(String message) {
    this.messageConsumer.accept(message);
  }

  public void setMessageConsumer(Consumer<String> messageConsumer) {
    this.messageConsumer = messageConsumer;
  }

  @Override
  public String toString() {
    return this.name;
  }
}
