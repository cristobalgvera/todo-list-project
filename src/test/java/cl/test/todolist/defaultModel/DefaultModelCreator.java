package cl.test.todolist.defaultModel;

import cl.test.todolist.constant.Model;
import cl.test.todolist.model.Credential;
import cl.test.todolist.model.Task;
import cl.test.todolist.model.TodoList;
import cl.test.todolist.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultModelCreator {
    @Autowired
    private DefaultModelFactory defaultModelFactory;

    public List<Object> createDefaultModel() {
        List<Object> basicsList = new ArrayList<>();
        basicsList.add((User) defaultModelFactory.create(Model.USER));
        basicsList.add((Credential) defaultModelFactory.create(Model.CREDENTIAL));
        basicsList.add((TodoList) defaultModelFactory.create(Model.TODO_LIST));
        basicsList.add((Task) defaultModelFactory.create(Model.TASK));
        return basicsList;
    }
}
