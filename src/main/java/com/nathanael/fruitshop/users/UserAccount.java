package com.nathanael.fruitshop.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserAccount {

    @Id
    @SequenceGenerator(name = "user_account_id_seq", sequenceName = "user_account_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_account_id_seq")
    @Column(updatable = false)
    private Long userId;

    @Column(nullable = false, length = 30)
    private String firstName;

    @Column(nullable = false, length = 30)
    private String lastName;

    @Column(nullable = false, length = 30, unique = true)
    private String email;

    @Column(nullable = false, length = 10)
    private String phone;

    @Column(length = 100)
    private String password;

    @Column
    private String image;

    @Enumerated(EnumType.STRING)
    private UserPosition userPosition;
}
