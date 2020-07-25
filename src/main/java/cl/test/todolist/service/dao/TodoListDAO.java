package cl.test.todolist.service.dao;

import cl.test.todolist.model.TodoList;
import cl.test.todolist.repository.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TodoListDAO implements DAO<TodoList, Long> {

    @Autowired
    private TodoListRepository todoListRepository;

    @Override
    public Optional<TodoList> get(Long id) {
        return Optional.ofNullable(todoListRepository.getOne(id));
    }

    @Override
    public Collection<TodoList> getAll() {
        return todoListRepository.findAll().stream()
                .filter(Objects::nonNull)
                .collect(Collectors.collectingAndThen(Collectors.toList(),
                        Collections::unmodifiableList));
    }

    @Override
    public TodoList save(TodoList todoList) {
        return todoListRepository.save(todoList);
    }

    @Override
    public TodoList update(TodoList todoList) {
        TodoList dbTodoList = get(todoList.getId())
                .orElseThrow(() -> new EntityNotFoundException("Not found todo list:" + todoList.getId()));

        if (!todoList.getTodoListName().equals(dbTodoList.getTodoListName()))
            dbTodoList.setTodoListName(todoList.getTodoListName());

        return todoListRepository.save(dbTodoList);
    }

    @Override
    public void delete(Long id) {
        todoListRepository.deleteById(id);
    }
}
