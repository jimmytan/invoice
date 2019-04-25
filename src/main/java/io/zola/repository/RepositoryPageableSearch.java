package io.zola.repository;

import org.springframework.data.domain.Page;

public interface RepositoryPageableSearch<T, C> {

  Page<T> search(C searchContext);

}
