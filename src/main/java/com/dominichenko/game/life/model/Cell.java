package com.dominichenko.game.life.model;

import lombok.Data;

/**
 * @author <a href="mailto:max@dominichenko.com">Max Dominichenko</a>
 */
@SuppressWarnings("WeakerAccess")
@Data
public class Cell implements Comparable<Cell> {

    private final int x;
    private final int y;

    public Cell(int x, int y) {
        if (x < 0) throw new IllegalArgumentException("X coordinate must be 0 or more");
        if (y < 0) throw new IllegalArgumentException("Y coordinate must be 0 or more");
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Cell o) {
        if (o == null) return -1;
        int result;
        result = x - o.x;
        if (result != 0) return result;
        result = y - o.y;
        return result;
    }
}
