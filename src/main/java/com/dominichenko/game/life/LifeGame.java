package com.dominichenko.game.life;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toList;

import com.dominichenko.game.life.model.Scene;
import java.io.File;
import java.nio.file.Files;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Stream;
import lombok.val;

/**
 * @author <a href="mailto:max@dominichenko.com">Max Dominichenko</a>
 */
@SuppressWarnings({"java:S106"})
public class LifeGame {

  private static final Scanner SCAN = new Scanner(System.in);

  @SuppressWarnings({"java:S2189", "InfiniteLoopStatement", "BusyWait"})
  public static void main(String[] args) throws Exception {
    val lines = args.length > 0
        ? Files.readAllLines(new File(args[0]).toPath())
        : Stream.generate(SCAN::nextLine)
            .takeWhile(Objects::nonNull)
            .takeWhile(not(String::isEmpty))
            .collect(toList());
    var scene = Scene.fromStrings('O', lines.toArray(new String[0]));

    while (true) {
      clearConsole();
      Stream.of(scene.toStrings('O', '.')).forEach(System.out::println);
      scene = scene.nextScene();
      Thread.sleep(300);
    }
  }

  private static void clearConsole() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}
