package pl.piotrjaniszewski.shoppingtips.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.piotrjaniszewski.shoppingtips.DTOs.UserDTO;
import pl.piotrjaniszewski.shoppingtips.domain.Role;
import pl.piotrjaniszewski.shoppingtips.domain.User;

import java.util.stream.Collectors;

@Component
public class UserToUserDTO implements Converter<User, UserDTO> {

    @Override
    public UserDTO convert(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setUsername(user.getUsername());
        userDTO.setLastName(user.getLastName());
        userDTO.setPassword(user.getPassword());
        userDTO.setRoleNames(
                user.getRoles().stream()
                        .map(Role::getName)
                        .collect(Collectors.toList()));

        return userDTO;
    }
}