package cl.test.todolist.service;

import cl.test.todolist.defaultModel.DefaultModelCreator;
import cl.test.todolist.model.*;
import cl.test.todolist.util.TestLog;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractTest {
    protected String currentTest;
    protected List<Object> defaultModelList;

    protected User testUser, dbUser;
    protected Credential testCredential, dbCredential;
    protected TodoList testTodoList, dbTodoList;
    protected Task testTask, dbTask;

    @Autowired
    protected TodoListService todoListService;

    @Autowired
    protected UserService userService;

    @Autowired
    protected CredentialService credentialService;

    @Autowired
    protected TaskService taskService;

    @Autowired
    protected TestLog log;

    @Autowired
    protected DefaultModelCreator defaultModelCreator;

    protected void createDefaultModel() {
        defaultModelList = defaultModelCreator
                .createDefaultModel();

        testUser = (User) defaultModelList.get(0);
        testCredential = (Credential) defaultModelList.get(1);
        testTodoList = (TodoList) defaultModelList.get(2);
        testTask = (Task) defaultModelList.get(3);
    }
}
