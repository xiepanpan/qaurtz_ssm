package com.xiepanpan.service.impl;

import com.xiepanpan.dao.IUserDao;
import com.xiepanpan.entity.User;
import com.xiepanpan.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public User getUser(User u) {
        return userDao.findUser(u);
    }
}
