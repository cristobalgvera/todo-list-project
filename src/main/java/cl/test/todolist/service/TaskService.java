package cl.test.todolist.service;

import cl.test.todolist.model.Task;
import cl.test.todolist.model.TodoList;
import cl.test.todolist.repository.TaskRepository;
import cl.test.todolist.service.dao.TaskDAO;
import cl.test.todolist.service.dao.TodoListDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@Service
public class TaskService {

    @Autowired
    private TaskDAO taskDAO;

    @Autowired
    private TodoListDAO todoListDAO;

    @Autowired
    private TaskRepository taskRepository;

    public Task save(Long todoListId, Task task) {
        TodoList todoList = todoListDAO.get(todoListId)
                .orElseThrow(() -> new EntityNotFoundException("Not found todo list: " + todoListId));
        task.setTodoList(todoList);
        todoList.addTask(task);
        return taskDAO.save(task);
    }

    public Collection<Task> findAll() {
        return taskDAO.getAll();
    }

    public Collection<Task> findAllByTodoListId(Long todoListId) {
        todoListDAO.get(todoListId)
                .orElseThrow(() -> new EntityNotFoundException("Not found todo list: " + todoListId));
        return taskRepository.findAllByTodoList_Id(todoListId)
                .orElse(null);
    }

    public Task findOne(Long id) {
        return taskDAO.get(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found task: " + id));
    }

    public Task update(Task task) {
        return taskDAO.update(task);
    }

    public void delete(Long id) {
        taskDAO.delete(id);
    }
}
