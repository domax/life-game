package com.dominichenko.game.life.model;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toSet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.val;

/**
 * @author <a href="mailto:max@dominichenko.com">Max Dominichenko</a>
 */
@Data
public class Scene {

  private final int width;
  private final int height;

  @Getter(AccessLevel.NONE)
  private final Set<Cell> cells = new HashSet<>();

  public static Scene fromStrings(char cellChar, String... lines) {
    if (lines == null || lines.length < 2)
      throw new IllegalArgumentException("Scene height must be 2 or more");

    val yRef = new AtomicInteger(-1);
    val wRef = new AtomicInteger();
    val cells = Arrays.stream(lines)
        .peek(line -> yRef.incrementAndGet())
        .peek(line -> wRef.set(Math.max(line.length(), wRef.get())))
        .flatMap(
            line ->
                IntStream.range(0, line.length())
                    .filter(x -> line.charAt(x) == cellChar)
                    .mapToObj(x -> new Cell(x, yRef.get())))
        .collect(toSet());

    val scene = new Scene(wRef.get(), lines.length);
    scene.cells.addAll(cells);
    return scene;
  }

  public Scene(int width, int height) {
    if (width < 2) throw new IllegalArgumentException("Scene width must be 2 or more");
    if (height < 2) throw new IllegalArgumentException("Scene height must be 2 or more");
    this.width = width;
    this.height = height;
  }

  public Scene addCell(int x, int y) {
    return addCell(new Cell(x, y));
  }

  public Scene addCell(Cell cell) {
    if (cell.getX() >= width) throw new IndexOutOfBoundsException("X position is out of bound");
    if (cell.getY() >= height) throw new IndexOutOfBoundsException("Y position is out of bound");
    cells.add(cell);
    return this;
  }

  public Optional<Cell> getCell(int x, int y) {
    return cells.stream().filter(cell -> cell.getX() == x && cell.getY() == y).findAny();
  }

  public Scene nextScene() {
    val scene = new Scene(width, height);
    IntStream.range(0, width)
        .mapToObj(x -> IntStream.range(0, width).mapToObj(y -> new Cell(x, y)))
        .flatMap(identity())
        .filter(
            c ->
                Optional.of(getNeighborsOf(c.getX(), c.getY()).size())
                    .filter(sz -> sz == 3 || sz == 2 && getCell(c.getX(), c.getY()).isPresent())
                    .isPresent())
        .forEach(scene::addCell);
    return scene;
  }

  Set<Cell> getNeighborsOf(int x, int y) {
    return Stream.of(
            getCell(x - 1, y),
            getCell(x - 1, y - 1),
            getCell(x - 1, y + 1),
            getCell(x + 1, y),
            getCell(x + 1, y - 1),
            getCell(x + 1, y + 1),
            getCell(x, y - 1),
            getCell(x, y + 1))
        .filter(Optional::isPresent)
        .map(Optional::get)
        .collect(toSet());
  }

  public String[] toStrings(char cellChar, char emptyChar) {
    return IntStream.range(0, height)
        .mapToObj(
            y ->
                IntStream.range(0, width)
                    .mapToObj(x -> getCell(x, y).isPresent() ? cellChar : emptyChar)
                    .map(Object::toString)
                    .collect(joining()))
        .toArray(String[]::new);
  }
}
