package cl.test.todolist.service.security;

import cl.test.todolist.model.Credential;
import cl.test.todolist.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserCredentialImpl implements UserDetails {

    private String email, password;
    private boolean active;
    private List<GrantedAuthority> authorities;

    public UserCredentialImpl(Credential credential) {
        this.email = credential.getEmail();
        this.password = credential.getPassword();
        this.active = credential.isActive();
        this.authorities = Arrays.stream(credential.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    /*
        Below returns was altered to avoid configure
        these three methods.
     */

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
