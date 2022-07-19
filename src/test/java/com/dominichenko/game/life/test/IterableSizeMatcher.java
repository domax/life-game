package com.dominichenko.game.life.test;

import java.util.stream.StreamSupport;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;

@SuppressWarnings("unused")
public class IterableSizeMatcher extends SizeMatcher<Iterable<?>> {

  @Factory
  public static Matcher<Iterable<?>> hasSize(int size) {
    return new IterableSizeMatcher(size);
  }

  private IterableSizeMatcher(int size) {
    super(size);
  }

  @Override
  protected String getClassName() {
    return "An iterable";
  }

  @Override
  protected boolean matchesSafely(Iterable<?> item) {
    return StreamSupport.stream(item.spliterator(), false).count() == size;
  }
}
