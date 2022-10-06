package com.nathanael.fruitshop.users;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class RegisterDto {

    @NotNull(message = "Email is required.")
    @Length(min = 1, max = 30)
    private String email;

    @NotNull(message = "Password is required.")
    private String password;
}
