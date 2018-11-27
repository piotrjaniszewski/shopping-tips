package pl.piotrjaniszewski.shoppingtips.converters;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.piotrjaniszewski.shoppingtips.DTOs.UserDTO;
import pl.piotrjaniszewski.shoppingtips.domain.Role;
import pl.piotrjaniszewski.shoppingtips.domain.User;
import pl.piotrjaniszewski.shoppingtips.repositories.RoleRepository;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserDTOtoUserTest {

    private UserDTOtoUser userDTOtoUser;

    @Mock
    private RoleRepository roleRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userDTOtoUser = new UserDTOtoUser(roleRepository);
    }

    @Test
    public void convert() {
        UserDTO userDto = new UserDTO();
        userDto.setUsername("username1");
        userDto.setId(1L);
        List<String> roleNames = new LinkedList<>();
        roleNames.add("ROLE_ADMIN");
        userDto.setRoleNames(roleNames);

        Role role = new Role("ROLE_ADMIN");

        when(roleRepository.findRoleByName(anyString())).thenReturn(role);

        User user = userDTOtoUser.convert(userDto);

        verify(roleRepository,times(1)).findRoleByName(anyString());
        assertEquals(user.getId(),userDto.getId());

        assertEquals(user.getUsername(),userDto.getUsername());
        assertEquals(user.getRoles().size(),1);
        assertEquals(user.getRoles().toArray(new Role[]{})[0].getName(),role.getName());
    }
}