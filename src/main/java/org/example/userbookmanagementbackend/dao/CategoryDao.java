package org.example.userbookmanagementbackend.dao;

import org.example.userbookmanagementbackend.bean.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category, Long> {
}
