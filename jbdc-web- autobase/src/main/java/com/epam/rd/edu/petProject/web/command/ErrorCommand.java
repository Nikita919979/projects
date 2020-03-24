package com.epam.rd.edu.petProject.web.command;


import com.epam.rd.edu.petProject.exception.ExceptionMessages;
import lombok.Getter;
import lombok.Setter;

import javax.servlet.http.HttpSession;

public class ErrorCommand extends AbstractFrontCommand{
    @Setter
    @Getter
    private String errorMessage = ExceptionMessages.DEFAULT_EXCEPTION.getMessage();

    @Override
    public void progress() {
        HttpSession session = request.getSession();
        session.setAttribute("errorMessage", errorMessage);
        forward(pagePathManager.getPath("errorPage"));
    }
}
