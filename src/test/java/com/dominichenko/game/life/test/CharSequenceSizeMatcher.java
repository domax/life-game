package com.dominichenko.game.life.test;

import org.hamcrest.Factory;
import org.hamcrest.Matcher;

@SuppressWarnings("unused")
public class CharSequenceSizeMatcher extends SizeMatcher<CharSequence> {

  @Factory
  public static Matcher<CharSequence> hasSize(int size) {
    return new CharSequenceSizeMatcher(size);
  }

  private CharSequenceSizeMatcher(int size) {
    super(size);
  }

  @Override
  protected boolean matchesSafely(CharSequence item) {
    return item.length() == size;
  }

  @Override
  protected String getClassName() {
    return "A char sequence";
  }
}
