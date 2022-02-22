package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private final EntityManager em;

    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<User> getAllUsers() {
        return em.createQuery("select distinct u from User u left join fetch u.roles", User.class).getResultList();
    }

    @Override
    public void saveUser(User user) {
        em.persist(user);
    }

    @Override
    public void updateUser(User user) {
        em.merge(user);
    }

    @Override
    public User getUser(Long id) {
        TypedQuery<User> tq = em.createQuery("select distinct u from User u left join fetch u.roles WHERE u.id=:param", User.class);
        return Optional.of(tq.setParameter("param", id).getSingleResult()).orElse(null);
    }

    @Override
    public void deleteUser(Long id) throws EntityNotFoundException {
        User user = em.getReference(User.class, id);
        em.remove(user);
    }

    @Override
    public User getUserByUsername(String name) {
        TypedQuery<User> tq = em.createQuery("select distinct u from User u left join fetch u.roles WHERE u.username=:param", User.class);
        return tq.setParameter("param", name).getSingleResult();
    }

}
