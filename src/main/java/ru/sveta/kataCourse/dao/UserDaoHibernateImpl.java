package ru.sveta.kataCourse.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.sveta.kataCourse.model.User;

import java.util.List;


@Repository
public class UserDaoHibernateImpl implements UserDao {

    @PersistenceContext()
    private final EntityManager entityManager;

    public UserDaoHibernateImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void createUser(User user) {
//        entityManager.joinTransaction();
        entityManager.persist(user);
    }

    @Override
    public User getById(long id) {
        return entityManager.createQuery("from User where id=:id", User.class)
                .setParameter("id",id)
                .getSingleResult();
    }

    @Override
    public List<User> getAllUsers() {

        return entityManager.createQuery("FROM User user", User.class)
                .getResultList();
    }

    @Override
    public void updateUser(long id, User user) {
        entityManager.joinTransaction();
        User someUser = getById(id);
        someUser.setName(user.getName());
        someUser.setSurname(user.getSurname());
        someUser.setEmail(user.getEmail());
        entityManager.persist(someUser);

    }

    @Override
    public void deleteById(long id) {
        entityManager.joinTransaction();
        try {
            entityManager.remove(getById(id));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

    }

    @Override
    public void deleteUser(User user) {
        entityManager.joinTransaction();
        findUser(user).forEach(someUser -> entityManager.remove(someUser.getId()));

    }

    @Override
    public List<User> findUser(User user) {
        return entityManager.createQuery("from User where name=:n and surname=:s and email=:e", User.class)
                .setParameter("n", user.getName())
                .setParameter("s", user.getSurname())
                .setParameter("e", user.getEmail())
                .getResultList();
    }

}
