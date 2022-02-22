package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();

    void saveUser(User user);

    void updateUser(User user);

    User getUser(Long id);

    void deleteUser(Long id);

    User getUserByUsername(String name);
}
