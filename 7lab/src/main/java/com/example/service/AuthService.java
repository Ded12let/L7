package com.example.service;

import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean authenticate(String login, String password) {
        System.out.println("Попытка входа: " + login + " / " + password);

        return userRepository.findByLogin(login)
                .map(user -> {
                    System.out.println("Найден пользователь: " + user.getLogin());
                    System.out.println("Пароль из БД: " + user.getPassword());
                    System.out.println("Введённый пароль: " + password);
                    return user.getPassword().equals(password);
                })
                .orElse(false);
    }

    public String getRole(String login) {
        return userRepository.findByLogin(login).map(User::getRole).orElse(null);
    }

    public boolean userExists(String login) {
        return userRepository.findByLogin(login).isPresent();
    }

    public void registerNewUser(String login, String password) {
        User newUser = new User();
        newUser.setLogin(login);
        newUser.setPassword(password);
        newUser.setRole("user");
        userRepository.save(newUser);
    }
}