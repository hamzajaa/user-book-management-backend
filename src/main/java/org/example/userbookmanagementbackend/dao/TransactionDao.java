package org.example.userbookmanagementbackend.dao;

import org.example.userbookmanagementbackend.bean.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDao extends JpaRepository<Transaction, Long> {
}
