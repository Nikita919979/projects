package com.epam.rd.edu.petProject.web.command.city;

import com.epam.rd.edu.petProject.dto.CityDto;
import com.epam.rd.edu.petProject.service.CityDtoServiceInterface;
import com.epam.rd.edu.petProject.service.SimpleServiceFactory;
import com.epam.rd.edu.petProject.web.command.AbstractFrontCommand;

import javax.servlet.http.HttpSession;

public class GetUpdateCityPageCommand extends AbstractFrontCommand {
    private final CityDtoServiceInterface cityDtoService = SimpleServiceFactory.getFactory().getCityService();

    @Override
    public void progress() {
        HttpSession session = request.getSession();
        CityDto cityDto = cityDtoService.read(Integer.parseInt(request.getParameter("city_id")));
        session.setAttribute("cityDto", cityDto);
        forward(pagePathManager.getPath("updateCityPage"));
    }
}

