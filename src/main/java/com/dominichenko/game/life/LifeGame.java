package com.dominichenko.game.life;

import com.dominichenko.game.life.model.Scene;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;
import lombok.var;

/**
 * @author <a href="mailto:max@dominichenko.com">Max Dominichenko</a>
 */
@SuppressWarnings({"java:S106"})
public class LifeGame {

  private static final Scanner SCAN = new Scanner(System.in);

  @SuppressWarnings({"java:S2189", "InfiniteLoopStatement", "BusyWait"})
  public static void main(String[] args) throws Exception {
    final List<String> lines;
    if (args.length > 0) {
      lines = Files.readAllLines(new File(args[0]).toPath());
    } else {
      lines = new ArrayList<>();
      String line;
      while ((line = SCAN.nextLine()) != null) {
        if (line.isEmpty()) break;
        lines.add(line);
      }
    }

    var scene = Scene.fromStrings('O', lines.toArray(new String[0]));
    while (true) {
      clearConsole();
      Stream.of(scene.toStrings('O', '.')).forEach(System.out::println);
      scene = scene.nextScene();
      Thread.sleep(500);
    }
  }

  private static void clearConsole() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}
