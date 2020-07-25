package cl.test.todolist.service.maintenance;

import cl.test.todolist.model.Credential;
import cl.test.todolist.model.User;

public class UserContext {
    private User user;
    private Credential credential;

    public UserContext(User user, Credential credential) {
        this.user = user;
        this.credential = credential;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }
}
