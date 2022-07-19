package com.dominichenko.game.life.model;

import lombok.Data;

/**
 * @author <a href="mailto:max@dominichenko.com">Max Dominichenko</a>
 */
@Data
public class Cell {

  private static final String E_COORD = " coordinate must be 0 or more";

  private final int x;
  private final int y;

  public Cell(int x, int y) {
    if (x < 0) throw new IllegalArgumentException("X" + E_COORD);
    if (y < 0) throw new IllegalArgumentException("Y" + E_COORD);
    this.x = x;
    this.y = y;
  }
}
