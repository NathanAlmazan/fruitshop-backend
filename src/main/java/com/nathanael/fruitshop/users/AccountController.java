package com.nathanael.fruitshop.users;

import com.nathanael.fruitshop.global.errors.EntityNotFoundException;
import com.nathanael.fruitshop.global.errors.InvalidRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AccountController {
    @Autowired private UserRepo userRepo;
    @Autowired private UserFactory userFactory;
    @Autowired private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody RegisterDto request) {
        UserAccount account = userRepo.findByEmail(request.getEmail());

        if (account == null) throw new EntityNotFoundException("Employee does not exists.");
        else if (account.getPassword() != null) throw new InvalidRequestException("Account already exists.");
        account.setPassword(passwordEncoder.encode(request.getPassword()));

        return new ResponseEntity<>(userFactory.entityToResponse(userRepo.save(account), null), HttpStatus.OK);
    }
}
