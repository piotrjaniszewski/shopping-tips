package pl.piotrjaniszewski.shoppingtips.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String username;
    private String email;
    private String name;
    private String lastName;
    private LocalDateTime creationDateTime;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private List<String> roleNames;
}
