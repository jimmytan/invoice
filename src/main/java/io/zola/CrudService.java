package io.zola;

import org.springframework.data.domain.Page;

public interface CrudService<M, C> {

  M create(M model);

  Page<M> search(C searchContext);


}
