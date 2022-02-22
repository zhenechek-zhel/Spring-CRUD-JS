package web.dao;

import web.model.Role;

import java.util.List;

public interface RoleDao {

    List<Role> getAllRoles();

    void saveRole(Role role);

    Role getRole(Long id);

    Role getRoleByName(String name);

    void deleteRole(Long id);
}
