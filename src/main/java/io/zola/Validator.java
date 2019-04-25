package io.zola;

public interface Validator<D> {

  void validate(D data);
}
