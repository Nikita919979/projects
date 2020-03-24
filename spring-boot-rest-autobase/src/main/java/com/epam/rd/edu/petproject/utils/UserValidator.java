package com.epam.rd.edu.petproject.utils;

import com.epam.rd.edu.petproject.dto.UserDto;
import com.epam.rd.edu.petproject.exception.WrongUserDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

  private static final Logger log = LoggerFactory.getLogger(UserValidator.class);

  //Minimum eight characters, at least one letter and one number
  private static final String passRegexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{5,30}$";
  private static final String emailRegexp = EmailRegexp.getEmailRegexp();
  private static final String defaultRegexp = "^[A-Za-z0-9_-]{3,20}$";

  public static UserValidator getValidator() {
    return new UserValidator();
  }

  public boolean ValidateUserDto(UserDto userDto) {
    validateName(userDto.getName());
    validateF_Name(userDto.getFamilyName());
    validateLogin(userDto.getLogin());
    validatePass(userDto.getPassword());
    validateEmail(userDto.getEmail());

    return true;
  }

  public boolean validatePass(String pass) {
    boolean result = baseValidate(pass);

    if (baseValidate(pass)) {
      result = pass.matches(passRegexp);
      logging(result, "Pass is valid", "Pass isn`t valid");
    }

    if (!result) {
      throw new WrongUserDataException();
    }
    return true;
  }

  public boolean validateLogin(String login) {
    boolean defaultValidate = defaultValidate(login);
    logging(defaultValidate, "Login is valid", "Login isn`t valid");
    if (!defaultValidate) {
      throw new WrongUserDataException();
    }
    return true;
  }

  public boolean validateName(String name) {
    boolean defaultValidate = defaultValidate(name);
    logging(defaultValidate, "Name is valid", "Name isn`t valid");
    if (!defaultValidate) {
      throw new WrongUserDataException();
    }
    return true;
  }

  public boolean validateF_Name(String f_Name) {
    boolean defaultValidate = defaultValidate(f_Name);
    logging(defaultValidate, "Family name is valid", "Family name isn`t valid");
    if (!defaultValidate) {
      throw new WrongUserDataException();
    }
    return true;
  }

  public boolean validateEmail(String email) {
    boolean result = baseValidate(email);

    if (baseValidate(email)) {
      result = email.matches(emailRegexp);
      logging(result, "Email is valid", "Email isn`t valid");
    }
    if (!result) {
      throw new WrongUserDataException();
    }
    return true;
  }

  private boolean defaultValidate(String str) {
    if (baseValidate(str)) {
      return str.matches(defaultRegexp);
    } else {
      return false;
    }
  }

  private boolean baseValidate(String str) {
    return !(str == null || str.isEmpty());
  }

  private void logging(boolean result, String right, String wrong) {
    if (result) {
      log.warn(right);
    } else {
      log.warn(wrong);
    }
  }
}
