package com.epam.rd.edu.petProject.web.command;

import com.epam.rd.edu.petProject.exception.FrontCommandException;
import com.epam.rd.edu.petProject.utils.PagePathManager;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Getter
@Setter
public abstract class AbstractFrontCommand implements FrontCommand {
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected ServletContext context;
    protected final PagePathManager pagePathManager = PagePathManager.getInstance();
    private final Logger log = LoggerFactory.getLogger(AbstractFrontCommand.class);

    @Override
    public abstract void progress();

    @Override
    public void init(HttpServletRequest request, HttpServletResponse response, ServletContext context) {
        this.request = request;
        this.response = response;
        this.context = context;
    }

    public void init(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    protected void forward(String target) {
        RequestDispatcher dispatcher = context.getRequestDispatcher(target);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
            throw new FrontCommandException(ex);
        }
    }

    protected void redirect(String target) {
        try {
            response.sendRedirect(target);
        } catch (IOException ex) {
            throw new FrontCommandException(ex);
        }
    }
}
