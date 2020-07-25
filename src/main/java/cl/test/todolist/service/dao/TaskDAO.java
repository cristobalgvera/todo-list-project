package cl.test.todolist.service.dao;

import cl.test.todolist.model.Task;
import cl.test.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TaskDAO implements DAO<Task, Long> {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Optional<Task> get(Long id) {
        return Optional.ofNullable(taskRepository.getOne(id));
    }

    @Override
    public Collection<Task> getAll() {
        return taskRepository.findAll().stream()
                .filter(Objects::nonNull)
                .collect(Collectors.collectingAndThen(Collectors.toList(),
                        Collections::unmodifiableList));
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task update(Task task) {
        Task dbTask = get(task.getId())
                .orElseThrow(() -> new EntityNotFoundException("Not found task: " + task.getId()));
        if (!task.getDescription().equals(dbTask.getDescription()))
            dbTask.setDescription(task.getDescription());

        if (task.getTask_date() != dbTask.getTask_date())
            dbTask.setTask_date(task.getTask_date());

        return taskRepository.save(dbTask);
    }

    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
