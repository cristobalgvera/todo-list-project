package cl.test.todolist.controller.rest;

import cl.test.todolist.model.TodoList;
import cl.test.todolist.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/admin/rest/todolist")
public class TodoListRestController {

    @Autowired
    private TodoListService todoListService;

    @PostMapping
    public void insert(@RequestBody TodoListContext todoListContext) {
        TodoList todoList = todoListContext.getTodoList();
        Long userId = todoListContext.getUserId();
        todoListService.save(userId, todoList);
    }

    @GetMapping
    public Collection<TodoList> readAll() {
        return todoListService.findAll();
    }

    @GetMapping("/user_id={id}")
    public Collection<TodoList> readAllByUserId(@PathVariable("id") Long userId) {
        return todoListService.findAllByUserId(userId);
    }

    @GetMapping("/{id}")
    public TodoList readOne(@PathVariable Long id) {
        return todoListService.findOne(id);
    }

    @PutMapping
    public TodoList update(@RequestBody TodoList todoList) {
        return todoListService.update(todoList);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        todoListService.delete(id);
    }

}

class TodoListContext {
    private TodoList todoList;
    private Long userId;

    public TodoList getTodoList() {
        return todoList;
    }

    public void setTodoList(TodoList todoList) {
        this.todoList = todoList;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
