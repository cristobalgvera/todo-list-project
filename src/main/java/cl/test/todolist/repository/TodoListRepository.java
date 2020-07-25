package cl.test.todolist.repository;

import cl.test.todolist.model.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface TodoListRepository extends JpaRepository<TodoList, Long> {
    Optional<Collection<TodoList>> findAllByUser_Id(Long userId);
}
