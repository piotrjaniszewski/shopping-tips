package pl.piotrjaniszewski.shoppingtips.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.piotrjaniszewski.shoppingtips.domain.Privilege;
import pl.piotrjaniszewski.shoppingtips.domain.Role;
import pl.piotrjaniszewski.shoppingtips.domain.User;
import pl.piotrjaniszewski.shoppingtips.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(s);

        UserBuilder builder = null;

        System.out.println();
        if(userOptional.isPresent()){
            User user = userOptional.get();
            builder = org.springframework.security.core.userdetails.User.withUsername(s);
            builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
//            builder.roles(user.getRoles().stream().map(Role::getName).collect(Collectors.toList()).toArray(new String[]{}));
            builder.authorities(getPrivileges(user.getRoles()));
            return builder.build();
        }
        throw new UsernameNotFoundException(s);
    }

    private List<GrantedAuthority> getPrivileges(Collection<Role> roles) {

        List<Privilege> collection = new ArrayList<>();
        for (Role role : roles) {
            collection.addAll(role.getPrivileges());
        }
        return new ArrayList<>(collection);
    }

}
