package cl.test.todolist.service.dao;

import cl.test.todolist.model.Credential;
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
        Credential dbCredential = get(credential.getId())
                .orElseThrow(() -> new EntityNotFoundException("Not found credential:" + credential.getId()));

        if (!credential.getEmail().equals(dbCredential.getEmail()))
            dbCredential.setEmail(credential.getEmail());

        if (!credential.getPassword().equals(dbCredential.getPassword()))
            dbCredential.setPassword(credential.getPassword());

        if (credential.isActive() != dbCredential.isActive())
            dbCredential.setActive(credential.isActive());

        if (!credential.getRoles().equals(dbCredential.getRoles()))
            dbCredential.setRoles(credential.getRoles());

        return credentialRepository.save(dbCredential);
    }

    @Override
    public void delete(Long id) {
        credentialRepository.deleteById(id);
    }
}
