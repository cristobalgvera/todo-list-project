package cl.test.todolist.defaultModel;

import cl.test.todolist.model.User;

public class DefaultUser implements DefaultModel<User> {

    @Override
    public User create() {
        User user = new User();
        {
            user.setFirstName("Foo");
            user.setLastName("Bar");
            user.setAge(20);
            user.setGender(false);
        }
        return user;
    }
}
