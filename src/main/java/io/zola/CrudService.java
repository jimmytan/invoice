package io.zola;

import java.util.List;

public interface CrudService<M, C> {

  M create(M model);

  List<M> search(C searchContext);


}
