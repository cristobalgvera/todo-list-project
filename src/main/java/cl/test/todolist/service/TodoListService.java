package cl.test.todolist.service;

import cl.test.todolist.model.TodoList;
import cl.test.todolist.model.User;
import cl.test.todolist.repository.TodoListRepository;
import cl.test.todolist.service.dao.TodoListDAO;
import cl.test.todolist.service.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@Service
public class TodoListService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private TodoListDAO todoListDAO;

    @Autowired
    private TodoListRepository todoListRepository;

    public TodoList save(Long userId, TodoList todoList) {
        User user = userDAO.get(userId)
                .orElseThrow(() -> new EntityNotFoundException("Not found user: " + userId));
        todoList.setUser(user);
        user.addTodoList(todoList);
        return todoListDAO.save(todoList);
    }

    public Collection<TodoList> findAll() {
        return todoListDAO.getAll();
    }

    public Collection<TodoList> findAllByUserId(Long userId) {
        userDAO.get(userId).orElseThrow(() -> new EntityNotFoundException("Not found user: " + userId));
        return todoListRepository.findAllByUser_Id(userId)
                .orElse(null);
    }

    public TodoList findOne(Long id) {
        return todoListDAO.get(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found todo list: " + id));
    }

    public TodoList update(TodoList todoList) {
        return todoListDAO.update(todoList);
    }

    public void delete(Long id) {
        todoListDAO.delete(id);
    }
}
