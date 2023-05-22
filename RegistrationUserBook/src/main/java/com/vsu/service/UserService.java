package com.vsu.service;

import com.vsu.entity.User;
import com.vsu.exception.ValidationException;
import com.vsu.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

import java.time.LocalDate;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getByLoginAndPassword(String login, String password) {
        User user = userRepository.selectByEmail(login);
        if (user == null) {
            throw new ValidationException("User is not found");
        }
        if (user.getPassword().equals(password)) {
            return user;
        }
        throw new ValidationException("Bad credentials");
    }

    public User insertUser(User user) {
        validateUser(user);
        userRepository.insert(user);
        return userRepository.selectByEmail(user.getEmail());
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User selectByIdUser(String id) {
        try {
            Long idL = Long.parseLong(id);
            User user = userRepository.selectById(idL);
            return userRepository.selectById(idL);
        } catch (NumberFormatException e) {
            throw new ValidationException("Bad ID");
        }
    }

    public void updateByID(User user) {
        validateUser(user);
        userRepository.updateByID(user);
    }

    private static void validateUser(User user) {
        if (user.getSurname() == null || user.getSurname().isBlank()
                || user.getSurname().length() > 50 || !StringUtils.isAlpha(user.getSurname())) {
            throw new ValidationException("Bad surname");
        }
        if (user.getName() == null || user.getName().isBlank()
                || user.getName().length() > 50 || !StringUtils.isAlpha(user.getName())) {
            throw new ValidationException("Bad name");
        }
        if (LocalDate.parse(user.getBirthday()).compareTo(LocalDate.now()) > 0) {
            throw new ValidationException("Bad date");
        }
        if (user.getPhone().indexOf("+") != 0 || !StringUtils.isNumeric(user.getPhone().substring(1))) {
            throw new ValidationException("Phone format is invalid");
        }
        if (!EmailValidator.getInstance().isValid(user.getEmail())) {
            throw new ValidationException("Email format is invalid");
        }
    }
}
