package com.example.springweb.entity;

import javax.persistence.Entity;
import javax.validation.constraints.*;

// !таблички не могут называться user
public class AppUser {
    // поле не пустое
//    @NotNull
//    // не меньше такого-то значения
//    @Min(45)
//    @Max(100)
    // заменяет 3 аннотации выше
    @Size(min = 4, max = 40)

    // будем собирать пользователя на основе формы
    private String login;
    // пользователь увидит месседж, если форма не пройдет проверку
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}", message = "Password might be...")
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
