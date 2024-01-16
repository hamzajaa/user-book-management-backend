package org.example.userbookmanagementbackend.security.bean;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(nullable = false, unique = true)
    private String userName;
    @Column(nullable = false, unique = true)
    @Email
    private String email;
    @Column(nullable = false, unique = true)
    private String numTel;
    @Column(nullable = false)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();

//    @OneToOne(mappedBy = "user")
//    private EventOrg eventOrg;
//
//    @OneToOne(mappedBy = "user")
//    private Participant participant;
}
