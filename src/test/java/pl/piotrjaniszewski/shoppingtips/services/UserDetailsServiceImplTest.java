package pl.piotrjaniszewski.shoppingtips.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.piotrjaniszewski.shoppingtips.domain.Privilege;
import pl.piotrjaniszewski.shoppingtips.domain.Role;
import pl.piotrjaniszewski.shoppingtips.domain.User;
import pl.piotrjaniszewski.shoppingtips.repositories.UserRepository;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserDetailsServiceImplTest {

    private UserDetailsService userDetailsService;

    @Mock
    UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userDetailsService = new UserDetailsServiceImpl(userRepository);
    }

    @Test
    public void loadUserByUsername() {
        User user = new User();
        user.setId(1L);
        user.setUsername("username");
        user.setPassword("password1");
        Set<Role> roleSet = new HashSet<>();

        Set<Privilege> privileges1 = new HashSet<>();
        Set<Privilege> privileges2 = new HashSet<>();

        privileges1.add(new Privilege("p1"));
        privileges1.add(new Privilege("p2"));
        privileges2.add(new Privilege("p3"));
        privileges2.add(new Privilege("p4"));
        Role role1 = new Role("ROLE_USER");
        Role role2 = new Role("ROLE_ADMIN");
        role1.setPrivileges(privileges1);
        role2.setPrivileges(privileges2);
        roleSet.add(role1);
        roleSet.add(role2);
        user.setRoles(roleSet);

        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        UserDetails userDetails = userDetailsService.loadUserByUsername("username");
        verify(userRepository,times(1)).findByUsername(anyString());

        SecureRandom secureRandom = new SecureRandom("ASD".getBytes());

        assertEquals(userDetails.getUsername(),user.getUsername());


        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        assertTrue(passwordEncoder.matches(user.getPassword(), userDetails.getPassword()));
        assertEquals(userDetails.getAuthorities().size(),4);
    }
}