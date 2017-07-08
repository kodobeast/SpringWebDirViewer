package ru.test.springwebdirviewer.service;

import ru.test.springwebdirviewer.annotation.ExecTime;
import ru.test.springwebdirviewer.dao.RoleDao;
import ru.test.springwebdirviewer.dao.UserDao;
import ru.test.springwebdirviewer.model.Role;
import ru.test.springwebdirviewer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getOne(1L));
        user.setRoles(roles);
        userDao.save(user);
    }

    @ExecTime
    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
