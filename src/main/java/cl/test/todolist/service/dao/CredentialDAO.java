package cl.test.todolist.service.dao;

import cl.test.todolist.model.Credential;
import cl.test.todolist.model.User;
import cl.test.todolist.repository.CredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CredentialDAO implements DAO<Credential, Long> {

    @Autowired
    private CredentialRepository credentialRepository;

    @Override
    public Optional<Credential> get(Long userId) {
        return Optional.ofNullable(credentialRepository.getOne(userId));
    }

    @Override
    public Collection<Credential> getAll() {
        return credentialRepository.findAll().stream()
                .filter(Objects::nonNull)
                .collect(Collectors.collectingAndThen(Collectors.toList(),
                        Collections::unmodifiableList));
    }

    @Override
    public Credential save(Credential credential) {
        return credentialRepository.save(credential);
    }

    @Override
    public Credential update(Credential credential) {
        Credential dbCredential = get(credential.getId()).orElseThrow(() -> new EntityNotFoundException("Not found:" + credential.getId()));
        dbCredential.setEmail(credential.getEmail());
        dbCredential.setPassword(credential.getPassword());
        dbCredential.setActive(credential.isActive());
        dbCredential.setRoles(credential.getRoles());
        return credentialRepository.save(dbCredential);
    }

    @Override
    public void delete(Credential credential) {
        credentialRepository.deleteById(credential.getId());
    }
}
