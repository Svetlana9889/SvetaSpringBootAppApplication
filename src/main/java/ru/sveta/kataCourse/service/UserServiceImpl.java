package ru.sveta.kataCourse.service;

import org.springframework.stereotype.Service;
import ru.sveta.kataCourse.dao.UserDao;
import ru.sveta.kataCourse.model.User;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public void createUser(User user) { userDao.createUser(user); }

    @Override
    public User getById(long id) { return userDao.getById(id); }

    @Override
    public List<User> getAllUsers() { return userDao.getAllUsers(); }

    @Override
    public void updateUser(long id, User user) { userDao.updateUser(id, user); }

    @Override
    public void deleteById(long id) {userDao.deleteById(id); }

    @Override
    public void deleteUser(User user) {userDao.deleteUser(user); }

    @Override
    public List<User> findUser(User user) {return userDao.findUser(user); }
}
