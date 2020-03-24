package com.epam.rd.edu.petProject.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionService {
    private static final Logger log = LoggerFactory.getLogger(ExceptionService.class);

    public String webExecute(AutobaseUncheckedException ex) {
        log.error(ex.getMessage());
        String msg = null;

        if (ex instanceof DaoException) {
            msg = ExceptionMessages.BASE_ERROR.getMessage();
        } else if (ex instanceof FrontCommandException) {
            msg = ExceptionMessages.FRONT_COMMAND_ERROR.getMessage();
        } else if (ex instanceof UserWithEmailExistException) {
            msg = ExceptionMessages.EMAIL_EXIST.getMessage();
        } else if (ex instanceof UserWithLoginExistException) {
            msg = ExceptionMessages.LOGIN_EXIST.getMessage();
        } else if (ex instanceof WrongEmailException) {
            msg = ExceptionMessages.WRONG_EMAIL.getMessage();
        } else if (ex instanceof WrongFNameException) {
            msg = ExceptionMessages.WRONG_FAMILY_NAME.getMessage();
        } else if (ex instanceof WrongLoginException) {
            msg = ExceptionMessages.WRONG_LOGIN.getMessage();
        } else if (ex instanceof WrongNameException) {
            msg = ExceptionMessages.WRONG_NAME.getMessage();
        } else if (ex instanceof WrongPassException) {
            msg = ExceptionMessages.WRONG_PASS.getMessage();
        } else {
            msg = ExceptionMessages.DEFAULT_EXCEPTION.getMessage();
        }

        return msg;
    }
}
