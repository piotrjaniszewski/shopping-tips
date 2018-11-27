package pl.piotrjaniszewski.shoppingtips.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.piotrjaniszewski.shoppingtips.domain.User;
import pl.piotrjaniszewski.shoppingtips.repositories.UserRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    private User user1;
    private User user2;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(userRepository);
        user1=user1();
        user2=user2();
    }

    @Test
    public void deleteById() {
        Long idToDelete = 1L;

        userService.deleteById(idToDelete);

        verify(userRepository,times(1)).deleteById(idToDelete);
    }

    @Test
    public void saveUser() {
        when(userRepository.save(any(User.class))).thenReturn(user1);

        User savedUser = userService.saveUser(user1);

        verify(userRepository,times(1)).save(any(User.class));
        assertEquals(savedUser.getId(),user1.getId());
        assertEquals(savedUser.getUsername(),user1.getUsername());
    }

    @Test
    public void findUserById() {
        Optional<User> user = Optional.of(user1);
        Long userID = 1L;

        when(userRepository.findById(userID)).thenReturn(user);

        User savedUser = userService.findUserById(userID);
        verify(userRepository,times(1)).findById(userID);
        assertEquals(savedUser.getUsername(),user.get().getUsername());
        assertEquals(savedUser.getId(),userID);
    }

    @Test
    public void findAllUsers() {
        List<User> userList = new LinkedList<>();
        userList.add(user1);
        userList.add(user2);

        when(userRepository.findAll()).thenReturn(userList);
        List<User> savedList = userService.findAllUsers();

        verify(userRepository,times(1)).findAll();
        assertEquals(savedList.size(),2);
    }

    @Test
    public void findUserByUsername() {

        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user1));

        User user = userService.findUserByUsername("username1");

        verify(userRepository,times(1)).findByUsername(anyString());
        assertEquals(user.getUsername(),user1.getUsername());
    }

    private User user1(){
        User user = new User();
        user.setId(1L);
        user.setUsername("Username1");

        return user;
    }

    private User user2(){
        User user = new User();
        user.setId(2L);
        user.setUsername("Username2");

        return user;
    }
}