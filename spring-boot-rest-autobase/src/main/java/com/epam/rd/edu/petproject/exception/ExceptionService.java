package com.epam.rd.edu.petproject.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionService {

  @ExceptionHandler(WrongUserDataException.class)
  public ResponseEntity<String> wrongUserDataException() {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(ExceptionMessages.WRONG_USER_DATA.getMessage());
  }

  @ExceptionHandler(UserWithLoginExistException.class)
  public ResponseEntity<String> loginExistException() {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(ExceptionMessages.LOGIN_EXIST.getMessage());
  }

  @ExceptionHandler(UserWithEmailExistException.class)
  public ResponseEntity<String> emailExistException() {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(ExceptionMessages.EMAIL_EXIST.getMessage());
  }

  @ExceptionHandler(DaoException.class)
  public ResponseEntity<String> DataBaseException() {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(ExceptionMessages.BASE_ERROR.getMessage());
  }

  @ExceptionHandler(UsernameNotFoundException.class)
  public ResponseEntity<String> userNotFoundException() {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(ExceptionMessages.USER_NOT_FOUND.getMessage());
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<String> entityNotFoundException() {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(ExceptionMessages.ENTITY_NOT_FOUND.getMessage());
  }
}
