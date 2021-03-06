package cl.test.todolist.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserServiceTest extends AbstractTest {
    @Test
    @Transactional
    protected void createUser() {
        currentTest = "INSERT USER TO DATABASE";
        log.start(currentTest);
        createDefaultModel();
        try {
            dbUser = userService.save(testUser, testCredential);
            dbCredential = credentialService.findOne(dbUser.getId());
        } catch (Exception e) {
            log.warning(e.fillInStackTrace());
        } finally {
            log.message(dbUser);
            log.message(dbCredential);
            assertEquals(testCredential.getPassword(), dbCredential.getPassword());
            if (dbCredential != null) userService.delete(dbUser.getId());
            log.finish(currentTest);
        }
    }

}
