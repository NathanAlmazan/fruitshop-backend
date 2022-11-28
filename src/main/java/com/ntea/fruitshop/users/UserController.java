package com.ntea.fruitshop.users;

import com.ntea.fruitshop.global.EntityCrudController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController implements EntityCrudController<UserDto, Long> {
    private final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping
    @Override
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto data) {
        return new ResponseEntity<>(userServices.create(data), HttpStatus.CREATED);
    }

    @PutMapping
    @Override
    public ResponseEntity<UserDto> update(@Valid @RequestBody UserDto data) {
        return new ResponseEntity<>(userServices.update(data), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<UserDto> delete(@PathVariable Long id) {
        return new ResponseEntity<>(userServices.delete(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<UserDto> getById(@PathVariable Long id, @RequestParam(required = false) List<String> additionalFields) {
        return new ResponseEntity<>(userServices.getDtoById(id, additionalFields), HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getByEmail(@PathVariable String email) {
        return new ResponseEntity<>(userServices.getByEmail(email), HttpStatus.OK);
    }

    @GetMapping
    @Override
    public ResponseEntity<List<UserDto>> getAll(@RequestParam(required = false) List<String> additionalFields) {
        return new ResponseEntity<>(userServices.getAll(additionalFields), HttpStatus.OK);
    }

    @PutMapping("/reset/{id}")
    public ResponseEntity<UserDto> resetAccount(@PathVariable Long id) {
        return new ResponseEntity<>(userServices.resetAccount(id), HttpStatus.OK);
    }

    @GetMapping("/names")
    public ResponseEntity<List<String>> getUserNames() {
        return new ResponseEntity<>(userServices.getAllAccountNames(), HttpStatus.OK);
    }
}
