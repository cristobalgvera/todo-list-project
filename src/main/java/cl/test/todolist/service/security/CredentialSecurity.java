package cl.test.todolist.service.security;

import cl.test.todolist.model.Credential;
import cl.test.todolist.model.User;
import cl.test.todolist.repository.CredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CredentialSecurity implements UserDetailsService {

    @Autowired
    private CredentialRepository credentialRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Credential> credential = credentialRepository.findByEmail(email);
        credential.orElseThrow(() -> new UsernameNotFoundException("Not found: " + email));
        return credential.map(UserCredentialImpl::new).get();
    }
}
