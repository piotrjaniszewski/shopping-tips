package pl.piotrjaniszewski.shoppingtips.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.piotrjaniszewski.shoppingtips.domain.Privilege;
import pl.piotrjaniszewski.shoppingtips.domain.Role;
import pl.piotrjaniszewski.shoppingtips.domain.User;
import pl.piotrjaniszewski.shoppingtips.repositories.PrivilegeRepository;
import pl.piotrjaniszewski.shoppingtips.repositories.RoleRepository;
import pl.piotrjaniszewski.shoppingtips.repositories.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Component
public class Bootstrap implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;

    public Bootstrap(UserRepository userRepository, RoleRepository roleRepository, PrivilegeRepository privilegeRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadProducts();
    }

    private void loadProducts(){
        Privilege readPrivilege
                = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege
                = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        Set<Privilege> adminPrivileges = new HashSet<>();
        adminPrivileges.add(readPrivilege);
        adminPrivileges.add(writePrivilege);

        Set<Privilege> userPrivileges = new HashSet<>();
        userPrivileges.add(readPrivilege);

        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", userPrivileges);

        Role adminRole = roleRepository.findRoleByName("ROLE_ADMIN");
        User user = new User();
        user.setUsername("user1");
        user.setPassword("p1");
        user.setEmail("dupa@dupa.dupa");
        user.setName("Dupa");
        user.setLastName("Smierdzi");
        Set<Role> roles = new HashSet<>();
        roles.add(adminRole);
        user.setRoles(roles);
        userRepository.save(user);

        System.out.println("Users loaded: "+userRepository.findAll().size());
    }

    private Privilege createPrivilegeIfNotFound(String name) {
        Privilege privilege = privilegeRepository.findPrivilegeByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }


    private Role createRoleIfNotFound(String name, Set<Privilege> privileges) {
        Role role = roleRepository.findRoleByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }
}