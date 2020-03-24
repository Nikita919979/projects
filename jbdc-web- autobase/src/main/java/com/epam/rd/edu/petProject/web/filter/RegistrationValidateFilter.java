package com.epam.rd.edu.petProject.web.filter;

import com.epam.rd.edu.petProject.model.User;
import com.epam.rd.edu.petProject.exception.AutobaseUncheckedException;
import com.epam.rd.edu.petProject.exception.ExceptionService;
import com.epam.rd.edu.petProject.dto.UserDto;
import com.epam.rd.edu.petProject.utils.UserValidator;
import com.epam.rd.edu.petProject.web.command.ErrorCommand;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationValidateFilter implements Filter {

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
            UserDto userDto = createUser(request);
            userValidator.ValidateUserDto(userDto);
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

    private UserDto createUser(ServletRequest request) {
        return UserDto.builder()
                .name(request.getParameter("name"))
                .familyName(request.getParameter("f_name"))
                .login(request.getParameter("login"))
                .password(request.getParameter("pass"))
                .role(User.Role.DRIVER)
                .email(request.getParameter("email"))
                .build();
    }
}
