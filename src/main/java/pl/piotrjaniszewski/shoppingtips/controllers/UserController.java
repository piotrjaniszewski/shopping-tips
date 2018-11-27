package pl.piotrjaniszewski.shoppingtips.controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.piotrjaniszewski.shoppingtips.DTOs.UserDTO;
import pl.piotrjaniszewski.shoppingtips.config.security.JwtTokenUtil;
import pl.piotrjaniszewski.shoppingtips.converters.UserToUserDTO;
import pl.piotrjaniszewski.shoppingtips.domain.User;
import pl.piotrjaniszewski.shoppingtips.services.UserService;

@RestController
public class UserController {

    private final UserService userService;
    private final UserToUserDTO userToUserDTO;

    public UserController(UserService userService, UserToUserDTO userToUserDTO) {
        this.userService = userService;
        this.userToUserDTO = userToUserDTO;
    }

    @JsonIgnore
    @JsonProperty("password")
    @GetMapping("/api/user/personalinfo")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO myPersonalInfo(@RequestHeader(name = "Authorization") String authHeader){
        String username = JwtTokenUtil.getUsernameFromToken(authHeader.replace("Bearer ", ""));
        User user = userService.findUserByUsername(username);

        return userToUserDTO.convert(user);
    }
}
