package com.epam.rd.edu.petProject.web.command.transit;

import com.epam.rd.edu.petProject.dto.TransitDto;
import com.epam.rd.edu.petProject.model.User;
import com.epam.rd.edu.petProject.service.SimpleServiceFactory;
import com.epam.rd.edu.petProject.service.TransitDtoServiceInterface;
import com.epam.rd.edu.petProject.web.command.AbstractFrontCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

public class GetAllTransitsCommand extends AbstractFrontCommand {
    private final TransitDtoServiceInterface transitDtoService = SimpleServiceFactory.getFactory().getTransitService();
    private static final Logger log = LoggerFactory.getLogger(GetAllTransitsCommand.class);


    @Override
    public void progress() {
        List<TransitDto> transitDtoList = transitDtoService.getAll();
        HttpSession session = request.getSession();
        if (User.Role.DRIVER.equals(session.getAttribute("role"))) {
            String login = (String) session.getAttribute("login");
            transitDtoList = transitDtoList.stream().filter(transitDto -> (transitDto.getDriver().getLogin().equals(login)))
                    .collect(Collectors.toList());
        }
        session.setAttribute("transitsList", transitDtoList);
        log.error("123");
        forward(pagePathManager.getPath("getAllTransitsPage"));
    }
}