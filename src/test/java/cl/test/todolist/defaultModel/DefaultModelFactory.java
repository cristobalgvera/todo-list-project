package cl.test.todolist.defaultModel;

import cl.test.todolist.constant.Model;
import cl.test.todolist.model.TodoList;
import org.springframework.stereotype.Component;

@Component
public class DefaultModelFactory {
    public Object create(String model) {
        DefaultModel defaultModel = null;
        switch (model) {
            case Model.USER:
                defaultModel = new DefaultUser();
                break;
            case Model.CREDENTIAL:
                defaultModel = new DefaultCredential();
                break;
            case Model.TODO_LIST:
                defaultModel = new DefaultTodoList();
                break;
            case Model.TASK:
                defaultModel = new DefaultTask();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + model);
        }
        return defaultModel.create();
    }
}
