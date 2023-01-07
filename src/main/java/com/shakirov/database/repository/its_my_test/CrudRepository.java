package com.shakirov.database.repository.its_my_test;

import java.util.Optional;

public interface CrudRepository<K, E> {

    Optional<E> findById (K id);

    void delete(K id);

}
