package cl.test.todolist.service;

import cl.test.todolist.model.Credential;
import cl.test.todolist.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private CredentialService credentialService;

    @Test
    public void createEncodedUser() {
        User user = new User();
        {
            user.setFirstName("Foo");
            user.setLastName("Bar");
            user.setAge(20);
            user.setSex(false);
        }
        Credential credential = new Credential();
        {
            credential.setEmail("foo@bar.cl");
            credential.setPassword("foobar");
            credential.setRoles("ROLE_USER");
            credential.setActive(true);
        }
        User dbUser = null;
        Credential dbCredential = null;
        try {
            dbUser = userService.save(user, credential);
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
        } finally {
            dbCredential = credentialService.findOne(dbUser.getId());
            assertEquals(credential.getPassword(), dbCredential.getPassword());
        }
        if (dbCredential != null) userService.delete(dbUser);
    }
}
