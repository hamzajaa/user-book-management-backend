package org.example.userbookmanagementbackend;

import org.example.userbookmanagementbackend.security.bean.Role;
import org.example.userbookmanagementbackend.security.bean.User;
import org.example.userbookmanagementbackend.security.dao.RoleDao;
import org.example.userbookmanagementbackend.security.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@SpringBootApplication
public class UserBookManagementBackendApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(UserBookManagementBackendApplication.class, args);
    }

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {

        User user = new User();
        user.setFirstName("hamza");
        user.setLastName("jaa");
        user.setUserName("hamzajaa_10");
        user.setEmail("hamzajaa@gmail.com");
        user.setNumTel("0664230265");
        user.setPassword(passwordEncoder.encode("123"));

        if (userDao.findByEmail(user.getEmail()).isEmpty()) {

            Role role = new Role("ADMIN");
            user.setRoles(Collections.singleton(role));

            User save = userDao.save(user);

            System.out.println("**************");

        }
    }
}
