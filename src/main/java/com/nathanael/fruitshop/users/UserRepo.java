package com.nathanael.fruitshop.users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserAccount, Long> {

    UserAccount findByEmail(String email);
}
