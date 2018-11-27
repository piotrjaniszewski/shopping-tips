package pl.piotrjaniszewski.shoppingtips.converters;

import org.junit.Before;
import org.junit.Test;
import pl.piotrjaniszewski.shoppingtips.DTOs.UserDTO;
import pl.piotrjaniszewski.shoppingtips.domain.Role;
import pl.piotrjaniszewski.shoppingtips.domain.User;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class UserToUserDTOTest {

    private UserToUserDTO userToUserDTO;

    @Before
    public void setUp() throws Exception {
        userToUserDTO = new UserToUserDTO();
    }

    @Test
    public void convert() {
        User user = new User();
        user.setUsername("username1");
        user.setId(1L);
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(new Role("ROLE_ADMIN"));
        user.setRoles(roleSet);

        UserDTO userDTO = userToUserDTO.convert(user);

        assertEquals(userDTO.getId(),user.getId());
        assertEquals(userDTO.getUsername(),user.getUsername());
        assertEquals(userDTO.getRoleNames().get(0), Arrays.stream(user.getRoles().toArray(new Role[]{})).map(Role::getName).collect(Collectors.toList()).get(0));
    }
}