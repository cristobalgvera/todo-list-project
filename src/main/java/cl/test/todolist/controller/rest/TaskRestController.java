package cl.test.todolist.controller.rest;

import cl.test.todolist.model.Task;
import cl.test.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/admin/rest/tasks")
public class TaskRestController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public void insert(@RequestBody TaskContext taskContext) {
        Task task = taskContext.getTask();
        Long todoListId = taskContext.getTodoListId();
        taskService.save(todoListId, task);
    }

    @GetMapping
    public Collection<Task> readAll() {
        return taskService.findAll();
    }

    @GetMapping("/todolist_id={id}")
    public Collection<Task> readAllByTodoListId(@PathVariable("id") Long todoListId) {
        return taskService.findAllByTodoListId(todoListId);
    }

    @GetMapping("/{id}")
    public Task readOne(@PathVariable Long id) {
        return taskService.findOne(id);
    }

    @PutMapping
    public Task update(@RequestBody Task task) {
        return taskService.update(task);
    }

    @DeleteMapping("/{}")
    public void delete (@PathVariable Long id) {
        taskService.delete(id);
    }
}

class TaskContext {
    private Task task;
    private Long todoListId;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Long getTodoListId() {
        return todoListId;
    }

    public void setTodoListId(Long todoListId) {
        this.todoListId = todoListId;
    }
}
