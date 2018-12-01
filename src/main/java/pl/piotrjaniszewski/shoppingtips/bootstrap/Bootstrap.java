package pl.piotrjaniszewski.shoppingtips.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.piotrjaniszewski.shoppingtips.domain.Privilege;
import pl.piotrjaniszewski.shoppingtips.domain.Role;
import pl.piotrjaniszewski.shoppingtips.domain.User;
import pl.piotrjaniszewski.shoppingtips.repositories.CategoryRepository;
import pl.piotrjaniszewski.shoppingtips.repositories.PrivilegeRepository;
import pl.piotrjaniszewski.shoppingtips.repositories.RoleRepository;
import pl.piotrjaniszewski.shoppingtips.repositories.UserRepository;

@Component
public class Bootstrap implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;
    private final CategoryRepository categoryRepository;

    public Bootstrap(UserRepository userRepository, RoleRepository roleRepository, PrivilegeRepository privilegeRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadUsers();
    }

    private void loadUsers(){
        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        Role adminRole = createRoleIfNotFound("ROLE_ADMIN");
        adminRole.addPrivilege(readPrivilege);
        adminRole.addPrivilege(writePrivilege);

        User user = new User();
        user.setUsername("user1");
        user.setPassword("p1");
        user.setEmail("dupa@dupa.dupa");
        user.setName("Dupa");
        user.setLastName("Smierdzi");
        user.addRole(adminRole);
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


    private Role createRoleIfNotFound(String name) {
        Role role = roleRepository.findRoleByName(name);
        if (role == null) {
            role = new Role(name);
            roleRepository.save(role);
        }
        return role;
    }
}