package com.epam.rd.edu.petProject.web.command;

import com.epam.rd.edu.petProject.exception.FrontCommandException;

import javax.servlet.http.HttpServletRequest;

public class CommandService {

    public static CommandService getCommandService() {
        return new CommandService();
    }

    public FrontCommand getCommand(HttpServletRequest request) {
        Class class_;
        FrontCommand result;

        int lastIndex = request.getServletPath().lastIndexOf('/') + 1;
        String commandClassName = "com.epam.rd.edu.petProject.web.command." +
                request.getServletPath().substring(lastIndex) + "Command";

        try {
            class_ = Class.forName(commandClassName);
            result = (FrontCommand) class_.newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
            throw new FrontCommandException(ex);
        }

        return result;
    }

    public FrontCommand getDefaultCommand() {
        return new DefaultPageCommand();
    }

    public FrontCommand getErrorCommand() {
        return new ErrorCommand();
    }
}
