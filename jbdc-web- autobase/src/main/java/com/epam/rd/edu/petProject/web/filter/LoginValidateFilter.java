package com.epam.rd.edu.petProject.web.filter;

import com.epam.rd.edu.petProject.exception.AutobaseUncheckedException;
import com.epam.rd.edu.petProject.exception.ExceptionService;
import com.epam.rd.edu.petProject.utils.UserValidator;
import com.epam.rd.edu.petProject.web.command.ErrorCommand;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginValidateFilter implements Filter {

    private final UserValidator userValidator = UserValidator.getValidator();
    private final ExceptionService exceptionService = new ExceptionService();

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        try {
            userValidator.validateLogin(request.getParameter("login"));
            userValidator.validateLogin(request.getParameter("pass"));
            chain.doFilter(request, response);
        } catch (AutobaseUncheckedException ex) {
            exceptionService.webExecute(ex);
            ErrorCommand errorCommand = new ErrorCommand();
            errorCommand.init(httpRequest, httpResponse);
            errorCommand.setErrorMessage(exceptionService.webExecute(ex));
            errorCommand.progress();
        }
    }

    @Override
    public void destroy() {

    }

}
