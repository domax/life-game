package com.dominichenko.game.life.model;

import static com.dominichenko.game.life.test.IterableSizeMatcher.hasSize;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertThrows;

import lombok.val;
import org.junit.Before;
import org.junit.Test;

/**
 * @author <a href="mailto:max@dominichenko.com">Max Dominichenko</a>
 */
public class SceneTest {

  private Scene sceneHardcoded;
  private String[] sceneLines, nextSceneLines;

  @Before
  public void beforeEach() {
    sceneHardcoded = new Scene(8, 6)
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
    sceneLines = new String[] {
        "......O.",
        "OOO...O.",
        "......O.",
        "........",
        "...OO...",
        "...OO..."};
    nextSceneLines = new String[] {
        ".O......",
        ".O...OOO",
        ".O......",
        "........",
        "...OO...",
        "...OO..."};
  }

  @Test
  public void testFromStrings() {
    val actual = Scene.fromStrings('O', sceneLines);
    assertThat(actual, is(sceneHardcoded));
    assertThat(actual.getHeight(), is(6));
    assertThat(actual.getWidth(), is(8));
  }

  @Test
  public void testFromStringsConstraints() {
    assertThat(
        assertThrows(IllegalArgumentException.class, () -> Scene.fromStrings('O', "...OOO..."))
            .getMessage(),
        containsString("Scene height"));
    assertThat(
        assertThrows(IllegalArgumentException.class, () -> Scene.fromStrings('O', ".", "O", "."))
            .getMessage(),
        containsString("Scene width"));
  }

  @Test
  public void testToStrings() {
    assertThat(sceneHardcoded.toStrings('O', '.'), is(sceneLines));
  }

  @Test
  public void testGetNeighborsOf() {
    assertThat(
        sceneHardcoded.getNeighborsOf(0, 0),
        allOf(hasSize(2), hasItems(new Cell(0, 1), new Cell(1, 1))));
    assertThat(
        sceneHardcoded.getNeighborsOf(1, 1),
        allOf(hasSize(2), hasItems(new Cell(0, 1), new Cell(2, 1))));
    assertThat(
        sceneHardcoded.getNeighborsOf(3, 4),
        allOf(hasSize(3), hasItems(new Cell(3, 5), new Cell(4, 4), new Cell(4, 5))));
    assertThat(
        sceneHardcoded.getNeighborsOf(4, 5),
        allOf(hasSize(3), hasItems(new Cell(3, 4), new Cell(3, 5), new Cell(4, 4))));
  }

  @Test
  public void testNextScene() {
    assertThat(sceneHardcoded.nextScene().toStrings('O', '.'), is(nextSceneLines));
  }
}
