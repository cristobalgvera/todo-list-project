package cl.test.todolist.defaultModel;

import cl.test.todolist.model.TodoList;

public class DefaultTodoList implements DefaultModel<TodoList> {
    @Override
    public TodoList create() {
        TodoList todoList = new TodoList();
        {
            todoList.setTodoListName("Foo list");
        }
        return todoList;
    }
}
