package dev.pirokiko.lanlords.playerstats.util;

import lombok.RequiredArgsConstructor;

import java.util.Iterator;
import java.util.function.Function;

@RequiredArgsConstructor
public class MappingIterator<T, R> implements Iterator<R> {
  private final Iterator<? extends T> iterator;
  private final Function<? super T, ? extends R> mapper;

  @Override
  public boolean hasNext() {
    return iterator.hasNext();
  }

  @Override
  public R next() {
    return mapper.apply(iterator.next());
  }
}
