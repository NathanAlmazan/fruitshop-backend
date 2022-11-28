package com.ntea.fruitshop.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private Long userId;

    @NotNull(message = "First name is required.")
    @Length(min = 1, max = 30)
    private String firstName;

    @NotNull(message = "Last name is required.")
    @Length(min = 1, max = 30)
    private String lastName;

    @NotNull(message = "Email is required.")
    @Length(min = 1, max = 30)
    private String email;

    @NotNull(message = "Phone number is required.")
    @Length(min = 1, max = 10)
    private String phone;

    @Length(max = 255)
    private String image;

    @JsonIgnore
    private String password;

    @ValueOfEnum(enumClass = UserPosition.class)
    private String userPosition;
}
