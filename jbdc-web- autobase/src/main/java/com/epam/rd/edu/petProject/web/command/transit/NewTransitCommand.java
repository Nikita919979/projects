package com.epam.rd.edu.petProject.web.command.transit;

import com.epam.rd.edu.petProject.dto.CarDto;
import com.epam.rd.edu.petProject.dto.CityDto;
import com.epam.rd.edu.petProject.dto.TransitDto;
import com.epam.rd.edu.petProject.dto.UserDto;
import com.epam.rd.edu.petProject.model.Transit;
import com.epam.rd.edu.petProject.service.*;
import com.epam.rd.edu.petProject.web.command.AbstractFrontCommand;

import javax.servlet.http.HttpSession;

public class NewTransitCommand extends AbstractFrontCommand {
    private final TransitDtoServiceInterface transitDtoService = SimpleServiceFactory.getFactory().getTransitService();
    private final CityDtoServiceInterface cityDtoService = SimpleServiceFactory.getFactory().getCityService();
    private final CarDtoServiceInterface carDtoService = SimpleServiceFactory.getFactory().getCarService();
    private final UserDtoServiceInterface userDtoService = SimpleServiceFactory.getFactory().getUserService();

    @Override
    public void progress() {
        TransitDto transitDto = getTransitDto();
        transitDtoService.create(transitDto);
        forward(pagePathManager.getPath("getAllTransitsPage"));
    }

    private TransitDto getTransitDto() {
        return TransitDto.builder()
                .car(getCarDto())
                .user(getUserDto())
                .city_from(getCityFromDto())
                .city_to(getCityToDto())
                .status(Transit.Status.valueOf(request.getParameter("status")))
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

    private CityDto getCityFromDto() {
        return cityDtoService.read(Integer.parseInt(request.getParameter("city_from")));
    }

    private UserDto getDriver() {
        return userDtoService.read(Integer.parseInt(request.getParameter("driver")));
    }

    private CityDto getCityToDto() {
        return cityDtoService.read(Integer.parseInt(request.getParameter("city_to")));
    }
}

