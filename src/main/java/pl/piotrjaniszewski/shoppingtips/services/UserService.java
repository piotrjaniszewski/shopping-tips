package pl.piotrjaniszewski.shoppingtips.services;

import org.springframework.stereotype.Service;
import pl.piotrjaniszewski.shoppingtips.DTOs.UserDTO;
import pl.piotrjaniszewski.shoppingtips.domain.User;

import java.util.List;

@Service
public interface UserService {
    void deleteById(Long id);
    User saveUser(User user);
    User findUserById(Long id);
    List<User> findAllUsers();
    User findUserByUsername(String username);
}