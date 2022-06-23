package poc.rc.rp.prac3;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import poc.rc.rp.commonutils.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

public class FileReaderService {

  public Flux<String> read(Path path) {
    return Flux.generate(
        openReader(path),
        read(),
        closeReader()
    );
  }

  private Callable<BufferedReader> openReader(Path path) {
    return () -> Files.newBufferedReader(path);
  }

  private BiFunction<BufferedReader, SynchronousSink<String>, BufferedReader> read() {
    return (br, sink) -> {
      try {
        String s = br.readLine();
        if(Objects.isNull(s))
          sink.complete();
        else
          sink.next(s);
      } catch (IOException e) {
        sink.error(e);
      }
      return br;
    };
  }

  private Consumer<BufferedReader> closeReader() {
    return br -> {
      try {
        br.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    };
  }

  public static void main(String[] args) {
    FileReaderService fileReaderService = new FileReaderService();
    Path path = Paths.get("src/main/resources/file01.txt");
    fileReaderService.read(path)
    .subscribe(Util.getSubscriber());
  }
}
