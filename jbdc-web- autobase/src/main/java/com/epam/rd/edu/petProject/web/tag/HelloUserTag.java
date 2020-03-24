package com.epam.rd.edu.petProject.web.tag;

import com.epam.rd.edu.petProject.dto.UserDto;
import com.epam.rd.edu.petProject.service.SimpleServiceFactory;
import com.epam.rd.edu.petProject.service.UserDtoServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class HelloUserTag extends TagSupport {
    private static final Logger logger = LoggerFactory.getLogger(HelloUserTag.class);
    private final UserDtoServiceInterface userDtoService = SimpleServiceFactory.getFactory().getUserService();

    @Override
    public int doStartTag() throws JspException {
        HttpSession session = pageContext.getSession();
        String login = session.getAttribute("login").toString();
        UserDto userDto = userDtoService.getByLogin(login);
        try {
            JspWriter writer = pageContext.getOut();
            writer.write(String.format("<b> %s %s </b>", userDto.getName(), userDto.getFamilyName()));
        } catch (IOException e) {
            logger.error(String.valueOf(e));
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
