package com.dominichenko.game.life.test;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
abstract class SizeMatcher<T> extends TypeSafeMatcher<T> {

  protected final int size;

  protected abstract String getClassName();

  @Override
  public void describeTo(Description description) {
    description.appendText(getClassName())
        .appendText(" has size ")
        .appendValue(size);
  }
}
