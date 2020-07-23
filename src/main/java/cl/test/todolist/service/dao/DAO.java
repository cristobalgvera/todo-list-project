package cl.test.todolist.service.dao;

import java.util.Collection;
import java.util.Optional;

public interface DAO<T, S> {
    Optional<T> get(S id);

    Collection<T> getAll();

    T save(T t);

    T update(T t);

    void delete(T t);
}
