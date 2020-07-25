package cl.test.todolist.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TodoListServiceTest extends AbstractTest {
    @Test
    @Transactional
    void createTodoList() {
        currentTest = "INSERT TODO LIST TO DATABASE";
        log.start(currentTest);
        createDefaultModel();
        try {
            dbUser = userService.save(testUser, testCredential);
            dbCredential = credentialService.findOne(dbUser.getId());
            dbTodoList = todoListService.save(dbUser.getId(), testTodoList);
            dbUser = userService.findOne(dbUser.getId());
        } catch (Exception e) {
            log.warning(e.fillInStackTrace());
        } finally {
            log.message(dbUser);
            log.message(dbTodoList);
            assertEquals(dbTodoList.getUser().getId(), dbUser.getId());
            if (dbCredential != null) userService.delete(dbUser.getId());
            log.finish(currentTest);
        }
    }
}
