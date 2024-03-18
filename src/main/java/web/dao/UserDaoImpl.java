package web.dao;

import web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUserList() {
        entityManager.getTransaction().begin();
        List<User> userList = entityManager.createQuery("FROM User").getResultList();
        entityManager.getTransaction().commit();
        return userList;
    }

    @Override
    public User getUserById(Long id) {
        entityManager.getTransaction().begin();
        User userById = entityManager.find(User.class, id);
        entityManager.getTransaction().commit();
        return userById;
    }

    @Override
    public void updateUserById(Long id, String newFirstName, String newLastName, int newAge) {
        entityManager.getTransaction().begin();
        User userToUpdate = entityManager.find(User.class, id);
        userToUpdate.setFirstName(newFirstName);
        userToUpdate.setLastName(newLastName);
        userToUpdate.setAge(newAge);
        String updateQuery = "UPDATE User SET firstName = :newFirstName, lastName = :newLastName, age = :newAge WHERE id = :id";
        entityManager.createQuery(updateQuery)
                .setParameter("id", id)
                .setParameter("newFirstName", userToUpdate.getFirstName())
                .setParameter("newLastName", userToUpdate.getLastName())
                .setParameter("newAge", userToUpdate.getAge())
                .executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteUserById(Long id) {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM User WHERE id = :id")
                .setParameter("id", id)
                .executeUpdate();
        entityManager.getTransaction().commit();
    }
}
