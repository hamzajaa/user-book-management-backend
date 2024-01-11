package org.example.userbookmanagementbackend.dao;

import org.example.userbookmanagementbackend.bean.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDao extends JpaRepository<Book, Long> {

}
