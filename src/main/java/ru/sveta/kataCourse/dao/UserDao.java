package ru.sveta.kataCourse.dao;


import ru.sveta.kataCourse.model.User;

import java.util.List;

public interface UserDao {
    void createUser(User user);
    User getById(long id);
    List<User> getAllUsers();
    void updateUser(long id,User user);
    void deleteById(long id);
    void deleteUser(User user);
    List<User> findUser(User user);



}
