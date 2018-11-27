package pl.piotrjaniszewski.shoppingtips.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.piotrjaniszewski.shoppingtips.services.UserService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class UserControllerTest {
    @Mock
    UserService userService;

    @InjectMocks
    private UserController customerController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
//                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();


    }
}