package cl.test.todolist.service.dao;

import cl.test.todolist.model.User;
import cl.test.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserDAO implements DAO<User, Long> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> get(Long id) {
        return Optional.ofNullable(userRepository.getOne(id));
    }

    @Override
    public Collection<User> getAll() {
        return userRepository.findAll().stream()
                .filter(Objects::nonNull)
                .collect(Collectors.collectingAndThen(Collectors.toList(),
                        Collections::unmodifiableList));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        User dbUser = get(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("Not found user:" + user.getId()));

        if (!user.getFirstName().equals(dbUser.getFirstName()))
            dbUser.setFirstName(user.getFirstName());

        dbUser.setLastName(user.getLastName());
        dbUser.setAge(user.getAge());
        return userRepository.save(dbUser);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
