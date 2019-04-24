package io.zola;

public interface Converter<S, T> {

  T to(S source);

  S from(T target);

}
