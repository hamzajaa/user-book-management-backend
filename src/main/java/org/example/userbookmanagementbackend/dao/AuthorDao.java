package org.example.userbookmanagementbackend.dao;

import org.example.userbookmanagementbackend.bean.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorDao extends JpaRepository<Author, Long> {
}
