package com.epam.rd.edu.petProject.web.command;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface FrontCommand {

    void progress() throws ServletException, IOException;

    void init(HttpServletRequest request, HttpServletResponse response, ServletContext context);
}
