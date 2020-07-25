package cl.test.todolist.defaultModel;

import cl.test.todolist.model.Credential;

public class DefaultCredential implements DefaultModel<Credential> {
    @Override
    public Credential create() {
        Credential credential = new Credential();
        {
            credential.setEmail("foo@bar.cl");
            credential.setPassword("foobar");
            credential.setRoles("ROLE_USER");
            credential.setActive(true);
        }
        return credential;
    }
}
