package com.dominichenko.game.life.model;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;

import static org.junit.Assert.*;

/**
 * @author <a href="mailto:max@dominichenko.com">Max Dominichenko</a>
 */
public class SceneTest {

    private final Scene sceneHardcoded = new Scene(8, 6)
            .addCell(6, 0)
            .addCell(0, 1)
            .addCell(1, 1)
            .addCell(2, 1)
            .addCell(6, 1)
            .addCell(6, 2)
            .addCell(3, 4)
            .addCell(4, 4)
            .addCell(3, 5)
            .addCell(4, 5);

    private final String[] sceneLines = new String[] {
            "......O.",
            "OOO...O.",
            "......O.",
            "........",
            "...OO...",
            "...OO..."};

    private final String[] nextSceneLines = new String[] {
            ".O......",
            ".O...OOO",
            ".O......",
            "........",
            "...OO...",
            "...OO..."};

    @Test
    public void testFromStrings() {
        Scene actual = Scene.fromStrings('O', sceneLines);
        assertEquals(sceneHardcoded, actual);
        assertEquals(6, actual.getHeight());
        assertEquals(8, actual.getWidth());
    }

    @Test
    public void testFromStringsConstraints() {
        try {
            Scene.fromStrings('O', "...OOO...");
            fail("Scene height constraint should be respected");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Scene height"));
        }
        try {
            Scene.fromStrings('O', ".", "O", ".");
            fail("Scene width constraint should be respected");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Scene width"));
        }
    }

    @Test
    public void testToStrings() {
        assertArrayEquals(sceneLines, sceneHardcoded.toStrings('O', '.'));
    }

    @Test
    public void testGetNeighborsOf() {
        assertEquals(
                new TreeSet<>(Arrays.asList(
                        new Cell(0, 1),
                        new Cell(1, 1))),
                new TreeSet<>(sceneHardcoded.getNeighborsOf(0, 0)));
        assertEquals(
                new TreeSet<>(Arrays.asList(
                        new Cell(0, 1),
                        new Cell(2, 1))),
                new TreeSet<>(sceneHardcoded.getNeighborsOf(1, 1)));
        assertEquals(
                new HashSet<>(Arrays.asList(
                        new Cell(3, 5),
                        new Cell(4, 4),
                        new Cell(4, 5))),
                sceneHardcoded.getNeighborsOf(3, 4));
        assertEquals(
                new TreeSet<>(Arrays.asList(
                        new Cell(3, 4),
                        new Cell(3, 5),
                        new Cell(4, 4))),
                new TreeSet<>(sceneHardcoded.getNeighborsOf(4, 5)));
    }

    @Test
    public void testNextScene() {
        assertArrayEquals(nextSceneLines, sceneHardcoded.nextScene().toStrings('O', '.'));
    }
}
