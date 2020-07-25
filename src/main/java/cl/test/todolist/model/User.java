package cl.test.todolist.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USER_TABLE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    @Id
    @GeneratedValue(generator = "USER_TABLE_SEQ")
    private Long id;

    @OneToOne(mappedBy = "user",
            cascade = CascadeType.ALL,
            optional = false,
            fetch = FetchType.LAZY)
//    @JsonBackReference
//    @JsonManagedReference
    @JsonIgnore
    private Credential credential;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TodoList> todoLists;

    @Column(length = 20)
    private String firstName;

    @Column(length = 25)
    private String lastName;

    @Column(length = 3)
    private int age;

    private boolean gender;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    public List<TodoList> getTodoLists() {
        return todoLists;
    }

    public void setTodoLists(List<TodoList> todoLists) {
        this.todoLists = todoLists;
    }

    public void addTodoList(TodoList todoList) {
        if (todoLists == null) todoLists = new ArrayList<>();
        todoLists.add(todoList);
        todoList.setUser(this);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean sex) {
        this.gender = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", credential=" + credential +
                ", todoLists=" + todoLists +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", sex=" + gender +
                '}';
    }
}
