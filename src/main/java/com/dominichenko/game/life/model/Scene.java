package com.dominichenko.game.life.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.val;
import lombok.var;

/**
 * @author <a href="mailto:max@dominichenko.com">Max Dominichenko</a>
 */
@Data
public class Scene {

  private final int width;
  private final int height;

  @Getter(AccessLevel.NONE) private final Set<Cell> cells = new HashSet<>();

  public static Scene fromStrings(char cellChar, String... lines) {
    if (lines == null || lines.length < 2)
      throw new IllegalArgumentException("Scene height must be 2 or more");

    val cells = new HashSet<Cell>();
    var width = 0;
    for (int y = 0; y < lines.length; ++y) {
      val line = lines[y];
      for (int x = 0; x < line.length(); ++x) {
        if (line.charAt(x) == cellChar)
          cells.add(new Cell(x, y));
      }
      if (width < line.length()) width = line.length();
    }
    val scene = new Scene(width, lines.length);
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
    if (x < 0 || x >= width)
      throw new IndexOutOfBoundsException("X position is out of bound");
    if (y < 0 || y >= height)
      throw new IndexOutOfBoundsException("Y position is out of bound");
    cells.add(new Cell(x, y));
    return this;
  }

  public Cell getCell(int x, int y) {
    return cells.stream()
        .filter(cell -> cell.getX() == x && cell.getY() == y)
        .findAny()
        .orElse(null);
  }

  public Scene nextScene() {
    val scene = new Scene(width, height);
    for (int x = 0; x < width; ++x) {
      for (int y = 0; y < height; ++y) {
        val neighborsSize = getNeighborsOf(x, y).size();
        if (neighborsSize == 3 || neighborsSize == 2 && getCell(x, y) != null)
          scene.addCell(x, y);
      }
    }
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
        .filter(Objects::nonNull)
        .collect(Collectors.toSet());
  }

  public String[] toStrings(char cellChar, char emptyChar) {
    String[] lines = new String[getHeight()];
    int w = getWidth();
    for (int y = 0; y < lines.length; ++y) {
      StringBuilder line = new StringBuilder();
      for (int x = 0; x < w; ++x)
        line.append(getCell(x, y) == null ? emptyChar : cellChar);
      lines[y] = line.toString();
    }
    return lines;
  }
}
