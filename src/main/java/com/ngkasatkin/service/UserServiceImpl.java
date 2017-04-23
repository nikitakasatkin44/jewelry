package com.ngkasatkin.service;

import com.ngkasatkin.dao.UserDao;
import com.ngkasatkin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public User findById(Integer id) {
        return userDao.findById(id);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public void saveOrUpdate(User user) {

        if (findById(user.getId()) == null) {
            userDao.save(user);
        } else {
            userDao.update(user);
        }
    }

    public void delete(int id) {
        userDao.delete(id);
    }


}
