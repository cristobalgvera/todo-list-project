package cl.test.todolist.service;

import cl.test.todolist.model.Credential;
import cl.test.todolist.model.User;
import cl.test.todolist.service.dao.DAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@Service
public class UserService {

    @Autowired
    private DAO<User, Long> userDAO;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User save(User user, Credential credential) {
//        user = userDAO.save(user);
        credential.setPassword(bCryptPasswordEncoder.encode(credential.getPassword()));
        user.setCredential(credential);
        credential.setUser(user);
//        credentialDAO.save(credential);
        return userDAO.save(user);
    }

    public Collection<User> findAll() {
        return userDAO.getAll();
    }

    public User findOne(Long id) {
        return userDAO.get(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found user: " + id));
    }

    public User update(User user) {
        return userDAO.update(user);
    }

    public void delete(Long id) {
        userDAO.delete(id);
    }
}
