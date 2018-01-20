package com.dominichenko.game.life;

import com.dominichenko.game.life.model.Scene;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * @author <a href="mailto:max@dominichenko.com">Max Dominichenko</a>
 */
public class LifeGame {

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        final List<String> lines = new ArrayList<>();
        String line;
        while ((line = scan.nextLine()) != null) {
            if (line.isEmpty()) break;
            lines.add(line);
        }
        final Scene scene = Scene.fromStrings('O', lines.toArray(new String[lines.size()]));
        Stream.of(scene.nextScene().toStrings('O', '.'))
                .forEach(System.out::println);
    }
}
