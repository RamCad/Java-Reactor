package poc.rc.rp.prac8;

public class SlackDemo {

  public static void main(String[] args) {
    SlackRoom slackRoom = new SlackRoom("Reactor");

    SlackMember sam = new SlackMember("Sam");
    SlackMember mike = new SlackMember("Mike");
    SlackMember jake = new SlackMember("Jake");

    slackRoom.joinRoom(sam);
    slackRoom.joinRoom(jake);

    sam.says("Hi All!");
    jake.says("Hey");
    sam.says("Sample text");

    slackRoom.joinRoom(mike);
    mike.says("Hey there");
  }
}
