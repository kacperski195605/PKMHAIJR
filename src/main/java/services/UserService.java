package services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import pkmhaijr.model.dbEntities.User;
import repositories.UserRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by patry on 23/04/17.
 */

@Service
@RequiredArgsConstructor(onConstructor = @__(@Inject))
@Log4j2
public class UserService {

    private final UserRepository userRepository;

    public User addUser(User user) {
        log.info("Adding user: " + user);
        return userRepository.save(user);
    }

    public User findUserByd(Integer id) {
        log.info("Searching for user with id " + id);
        return userRepository.findOne(id);
    }

    public List<User> findAllUsers() {
        log.info("Getting all users");
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public void deleteUser(User user) {
        log.info("Deleting user: " + user);
        userRepository.delete(user);
    }

    public User updateUser(User newUser) {
        log.info("Updating user: " + newUser);
        User oldUser = findUserByd(newUser.getId());
        if (oldUser != null) {
            return userRepository.save(newUser);
        } else {
            throw new IllegalArgumentException("There is no user with id " + newUser.getId() + " in the database");
        }
    }
}
