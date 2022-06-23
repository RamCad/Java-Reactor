package poc.rc.rp.prac1;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import poc.rc.rp.Util;
import reactor.core.publisher.Mono;

public class FileService {

  private static final Path PATH = Paths.get("src/main/resources");

  public static void main(String[] args) {
    read("file1.txt")
        .subscribe(Util.onNext(), Util.onError(), Util.onComplete());

    write("file3.txt", "This is file3")
        .subscribe(Util.onNext(), Util.onError(), Util.onComplete());

    delete("file3.txt")
        .subscribe(Util.onNext(), Util.onError(), Util.onComplete());
  }

  public static Mono<String> read(String fileName) {
    return Mono.fromSupplier(() -> readFile(fileName));
  }

  public static Mono<Void> write(String fileName, String content) {
    return Mono.fromRunnable(() -> writeFile(fileName, content));
  }

  public static Mono<Void> delete(String fileName) {
    return Mono.fromRunnable(() -> deleteFile(fileName));
  }

  private static String readFile(String fileName) {
    try {
//      return Files.readString(PATH.resolve(fileName)); // JAVA 11
      return new String(Files.readAllBytes(PATH.resolve(fileName)));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static void writeFile(String filename, String content) {
    try {
//      Files.writeString(PATH.resolve(filename), content); // JAVA 11
      Files.write(PATH.resolve(filename), Arrays.asList(content), StandardCharsets.UTF_8,
          StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static void deleteFile(String fileName) {
    try {
      Files.delete(PATH.resolve(fileName));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
