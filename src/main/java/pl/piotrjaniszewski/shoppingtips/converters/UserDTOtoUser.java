package pl.piotrjaniszewski.shoppingtips.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.piotrjaniszewski.shoppingtips.DTOs.UserDTO;
import pl.piotrjaniszewski.shoppingtips.domain.User;
import pl.piotrjaniszewski.shoppingtips.repositories.RoleRepository;

import java.util.stream.Collectors;

@Component
public class UserDTOtoUser implements Converter<UserDTO, User> {

    private final RoleRepository roleRepository;

    public UserDTOtoUser(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public User convert(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(userDTO.getPassword());
        user.setRoles(
                userDTO.getRoleNames().stream()
                    .map(roleRepository::findRoleByName)
                    .collect(Collectors.toSet()));
        user.setCreationDateTime(userDTO.getCreationDateTime());
        return user;
    }
}