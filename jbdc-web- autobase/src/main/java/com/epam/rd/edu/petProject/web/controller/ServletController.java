package com.epam.rd.edu.petProject.web.controller;

import com.epam.rd.edu.petProject.exception.AutobaseUncheckedException;
import com.epam.rd.edu.petProject.exception.ExceptionService;
import com.epam.rd.edu.petProject.web.command.CommandService;
import com.epam.rd.edu.petProject.web.command.ErrorCommand;
import com.epam.rd.edu.petProject.web.command.FrontCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletController extends HttpServlet {
    private final Logger log = LoggerFactory.getLogger(ServletController.class);
    private final ExceptionService exceptionService = new ExceptionService();
    private final CommandService commandService = new CommandService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        execute(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        execute(req, resp);
    }

    private void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            FrontCommand command = commandService.getCommand(req);
            log.info("used command: " + command.getClass().toString());
            log.error(command.getClass().toString());
            command.init(req, resp, getServletContext());
            command.progress();
        }catch (AutobaseUncheckedException ex) {
            ErrorCommand errorCommand = new ErrorCommand();
            errorCommand.init(req, resp, getServletContext());
            errorCommand.setErrorMessage(exceptionService.webExecute(ex));
            errorCommand.progress();
        }
    }
}
