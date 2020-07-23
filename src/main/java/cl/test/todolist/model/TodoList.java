package cl.test.todolist.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TodoList {
    @Id
    @GeneratedValue(generator = "TODO_LIST_SEQ")
    private Long id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "USER_ID")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "todoList", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Task> tasks;

    @Column(length = 50)
    private String todoListName;

    public TodoList() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTodoListName() {
        return todoListName;
    }

    public void setTodoListName(String todoListName) {
        this.todoListName = todoListName;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        if (tasks == null) tasks = new ArrayList<>();
        tasks.add(task);
        task.setTodoList(this);
    }

    @Override
    public String toString() {
        return "TodoList{" +
                "id=" + id +
                ", user=" + user.getId() +
                ", tasks=" + tasks +
                ", todoListName='" + todoListName + '\'' +
                '}';
    }
}

