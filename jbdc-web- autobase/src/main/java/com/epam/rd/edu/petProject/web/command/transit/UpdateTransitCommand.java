package com.epam.rd.edu.petProject.web.command.transit;

import com.epam.rd.edu.petProject.dto.CarDto;
import com.epam.rd.edu.petProject.dto.CityDto;
import com.epam.rd.edu.petProject.dto.TransitDto;
import com.epam.rd.edu.petProject.dto.UserDto;
import com.epam.rd.edu.petProject.model.Transit;
import com.epam.rd.edu.petProject.model.User;
import com.epam.rd.edu.petProject.service.*;
import com.epam.rd.edu.petProject.web.command.AbstractFrontCommand;

import javax.servlet.http.HttpSession;

public class UpdateTransitCommand extends AbstractFrontCommand {
    private final CarDtoServiceInterface carDtoService = SimpleServiceFactory.getFactory().getCarService();
    private final CityDtoServiceInterface cityDtoService = SimpleServiceFactory.getFactory().getCityService();
    private final UserDtoServiceInterface userDtoService = SimpleServiceFactory.getFactory().getUserService();
    private final TransitDtoServiceInterface transitDtoService = SimpleServiceFactory.getFactory().getTransitService();

    @Override
    public void progress() {
        HttpSession session = request.getSession();
        TransitDto transitDto = null;
        if (User.Role.DRIVER.equals(session.getAttribute("role"))) {
            transitDto = transitDtoService.read(Integer.parseInt(request.getParameter("transitDto_id")));
            transitDto.setStatus(Transit.Status.valueOf(request.getParameter("status")));
        } else {
            transitDto = getTransitDto();
        }
        transitDtoService.update(transitDto);
        forward(pagePathManager.getPath("getAllTransitsPage"));
    }

    private TransitDto getTransitDto() {
        return TransitDto.builder()
                .id(Integer.parseInt(request.getParameter("transitDto_id")))
                .city_to(getCityToDto())
                .city_from(getCityFromDto())
                .status(Transit.Status.valueOf(request.getParameter("status")))
                .car(getCarDto())
                .user(getUserDto())
                .driver(getDriver())
                .build();
    }

    private CarDto getCarDto() {
        return carDtoService.read(Integer.parseInt(request.getParameter("car")));
    }

    private UserDto getUserDto() {
        HttpSession session = request.getSession();
        return userDtoService.getByLogin(String.valueOf(session.getAttribute("login")));
    }

    private UserDto getDriver() {
        return userDtoService.read(Integer.parseInt(request.getParameter("driver")));
    }

    private CityDto getCityFromDto() {
        return cityDtoService.read(Integer.parseInt(request.getParameter("city_from")));
    }

    private CityDto getCityToDto() {
        return cityDtoService.read(Integer.parseInt(request.getParameter("city_to")));
    }
}

