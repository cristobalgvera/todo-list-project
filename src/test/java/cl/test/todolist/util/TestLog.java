package cl.test.todolist.util;

import org.springframework.stereotype.Component;

@Component
public class TestLog {
    public void start(String testName) {
        StringBuilder stringBuilder = new StringBuilder("***** TEST:  START *****\n");
        stringBuilder.insert(12, testName);
        System.out.println(stringBuilder.toString());
    }

    public void finish(String testName) {
        StringBuilder stringBuilder = new StringBuilder("\n***** TEST:  FINISH *****");
        stringBuilder.insert(13, testName);
        System.out.println(stringBuilder.toString());
    }

    public void message(Object log) {
        StringBuilder stringBuilder = new StringBuilder(">INFO: ");
        stringBuilder.append(log);
        System.out.println(stringBuilder.toString());
    }

    public void warning(Object log) {
        StringBuilder stringBuilder = new StringBuilder("> WARN: ");
        stringBuilder.append(log);
        System.err.print(stringBuilder);
    }
}
