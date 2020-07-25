package cl.test.todolist.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TaskServiceTest extends AbstractTest {
    @Test
    @Transactional
    void createTask() {
        currentTest = "INSERT CREATE TASK INTO DATABASE";
        log.start(currentTest);
        createDefaultModel();
        try {
            dbUser = userService.save(testUser, testCredential);
            dbCredential = credentialService.findOne(dbUser.getId());
            dbTodoList = todoListService.save(dbUser.getId(), testTodoList);
            dbTask = taskService.save(dbTodoList.getId(), testTask);
            dbUser = userService.findOne(dbUser.getId());
        } catch (Exception e) {
            log.warning(e.fillInStackTrace());
        } finally {
            log.message(dbUser);
            log.message(dbTodoList);
            log.message(dbTask);
            assertEquals(dbTask.getTodoList().getId(), dbTodoList.getId());
            if (dbCredential != null) userService.delete(dbUser.getId());
            log.finish(currentTest);
        }
    }
}
