package cl.test.todolist.rest;

import cl.test.todolist.model.Credential;
import cl.test.todolist.model.User;
import cl.test.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping
    public void insert(@RequestBody UserContext userContext) {
        User user = userContext.getUser();
        Credential credential = userContext.getCredential();
        userService.save(user, credential);
    }

    @GetMapping
    public Collection<User> readAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User readOne(@PathVariable Long id) {
        return userService.findOne(id);
    }

    @PutMapping
    public User updateOne(@RequestBody User user) {
        return userService.update(user);
    }

    @DeleteMapping
    public void deleteOne(@RequestBody User user) {
        userService.delete(user);
    }
}

class UserContext {
    private User user;
    private Credential credential;

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
