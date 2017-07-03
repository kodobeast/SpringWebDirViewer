package ru.test.springwebdirviewer.dao;

import ru.test.springwebdirviewer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
