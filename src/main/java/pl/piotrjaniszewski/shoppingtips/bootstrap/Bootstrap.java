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
        loadRoles();
        loadUsers();
    }

    private void loadUsers(){
        User adminUser = new User();
        adminUser.setUsername("user1");
        adminUser.setPassword("p1");
        adminUser.setEmail("dupa@dupa.dupa");
        adminUser.setName("Dupa");
        adminUser.setLastName("Smierdzi");
        adminUser.addRole(createRoleIfNotFound("ADMIN"));
        userRepository.save(adminUser);

        System.out.println("Users loaded: "+userRepository.findAll().size());
    }

    private void loadRoles(){
        Privilege userPrivilege = createPrivilegeIfNotFound("USER_PRIVILEGE");
        Privilege moderatorPrivilege = createPrivilegeIfNotFound("MODERATOR_PRIVILEGE");
        Privilege adminPrivilege = createPrivilegeIfNotFound("ADMIN_PRIVILEGE");

        Role userRole = createRoleIfNotFound("USER");
        Role moderatorRole = createRoleIfNotFound("MODERATOR");
        Role adminRole = createRoleIfNotFound("ADMIN");

        userRole.addPrivilege(userPrivilege);
        roleRepository.save(userRole);

        moderatorRole.addPrivilege(userPrivilege);
        moderatorRole.addPrivilege(moderatorPrivilege);
        roleRepository.save(moderatorRole);

        adminRole.addPrivilege(userPrivilege);
        adminRole.addPrivilege(moderatorPrivilege);
        adminRole.addPrivilege(adminPrivilege);
        roleRepository.save(adminRole);
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