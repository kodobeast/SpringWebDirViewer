package ru.test.springwebdirviewer.dao;

import ru.test.springwebdirviewer.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {
}
