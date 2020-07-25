package cl.test.todolist.repository;

import cl.test.todolist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Collection<Task>> findAllByTodoList_Id(Long todoListId);
}
