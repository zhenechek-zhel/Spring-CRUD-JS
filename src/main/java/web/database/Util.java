package web.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class Util {

    private final UserService userService;
    private final RoleService roleService;
    Set<Role> defaultRoles;

    @Autowired
    public Util(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void postConstruct(){

        addRolesToDB();
        addUsersToDB();

    }

    private void addRolesToDB(){

        List<Role> roleList = null;
        try {
            roleList = roleService.getAllRoles();
        } catch (Exception ignore) {
        }

        if (roleList == null || roleList.isEmpty()) {
            for (StandartRoles role : StandartRoles.values()) {
                roleService.saveRole(new Role(role));
            }
        }
    }

    public void addUsersToDB(){

        Role roleUser = roleService.getRoleByName(StandartRoles.ROLE_USER.name());
        Role roleAdmin = roleService.getRoleByName(StandartRoles.ROLE_ADMIN.name());

        userService.saveUsers(
                new User("Admin", "admin", "admin", "admin@mail.ru")
                        .addRolesToUser(roleUser, roleAdmin),

                new User("User", "user", "user", "user@mail.ru")
                        .addRoleToUser(roleUser),

                new User("Max", "max", "max", "max@mail.ru")
                        .addRoleToUser(roleUser),

                new User("Liza", "liza", "123", "liza@mail.ru")
                        .addRoleToUser(roleUser));
    }

    @Bean
    public Set<Role> getDefaultRoles() {
        if (defaultRoles == null || defaultRoles.isEmpty()) {
            defaultRoles = new HashSet<>(roleService.getAllRoles());
        }
        return defaultRoles;
    }
}

