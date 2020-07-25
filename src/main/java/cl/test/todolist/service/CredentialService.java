package cl.test.todolist.service;

import cl.test.todolist.model.Credential;
import cl.test.todolist.service.dao.DAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@Service
public class CredentialService {

    @Autowired
    private DAO<Credential, Long> credentialDAO;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Credential save(Long userId, Credential credential) {
        credential.setId(userId);
        credential.setPassword(bCryptPasswordEncoder.encode(credential.getPassword()));
        return credentialDAO.save(credential);
    }

    public Collection<Credential> findAll() {
        return credentialDAO.getAll();
    }

    public Credential findOne(Long userId) {
        return credentialDAO.get(userId)
                .orElseThrow(() -> new EntityNotFoundException("Not found credential: " + userId));
    }

    public Credential update(Credential credential) {
        credential.setPassword(bCryptPasswordEncoder.encode(credential.getPassword()));
        return credentialDAO.update(credential);
    }

    public void delete(Long id) {
        credentialDAO.delete(id);
    }
}
