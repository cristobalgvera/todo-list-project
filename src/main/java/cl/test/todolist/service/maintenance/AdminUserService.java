package cl.test.todolist.service.maintenance;

import cl.test.todolist.model.Credential;
import cl.test.todolist.model.User;
import cl.test.todolist.service.CredentialService;
import cl.test.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminUserService {

    @Autowired
    private UserService userService;

    @Autowired
    private CredentialService credentialService;

    public List<UserContext> usersCredentialsList() {
        List<UserContext> usersCredentials = new ArrayList<>();
        List<User> users = (List<User>) userService.findAll();
        List<Credential> credentials = (List<Credential>) credentialService.findAll();
        for (int i = 0; i < users.size(); i++) {
            usersCredentials.add(new UserContext(users.get(i), credentials.get(i)));
        }
        return usersCredentials;
    }
}

