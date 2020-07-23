package cl.test.todolist.service;

import cl.test.todolist.model.Credential;
import cl.test.todolist.model.User;
import cl.test.todolist.service.dao.DAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@Service
public class UserService {

    @Autowired
    private DAO<User, Long> userDAO;

    @Autowired
    private DAO<Credential, Long> credentialDAO;

    public User save(User user, Credential credential) {
        user = userDAO.save(user);
        user.setCredential(credential);
        credential.setUser(user);
        credentialDAO.save(credential);
        return user;
    }

    public Collection<User> findAll() {
        return userDAO.getAll();
    }

    public User findOne(Long id) {
        return userDAO.get(id).orElseThrow(() -> new EntityNotFoundException("Not found: " + id));
    }

    public User update(User user) {
        return userDAO.update(user);
    }

    public void delete(User user) {
        userDAO.delete(user);
    }
}
