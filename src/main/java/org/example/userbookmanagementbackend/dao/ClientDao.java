package org.example.userbookmanagementbackend.dao;

import org.example.userbookmanagementbackend.bean.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientDao extends JpaRepository<Client,Long> {
}
