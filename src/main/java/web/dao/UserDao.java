package web.dao;

import web.model.User;
import java.util.List;

public interface UserDao {

    void addUser(User user);

    List<User> getUserList();

    User getUserById(Long id);

    void updateUserById(Long id, String firstName, String lastName, String email);

    void deleteUserById(Long id);
}
