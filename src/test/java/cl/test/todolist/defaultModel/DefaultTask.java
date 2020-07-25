package cl.test.todolist.defaultModel;

import cl.test.todolist.model.Task;

import java.util.Date;

public class DefaultTask implements DefaultModel<Task> {
    @Override
    public Task create() {
        Task task = new Task();
        {
            task.setDescription("Make some tests to ensure quality");
            task.setTask_date(new Date());
        }
        return task;
    }
}
