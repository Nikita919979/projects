package com.epam.rd.edu.petProject.web.command.city;

import com.epam.rd.edu.petProject.dto.CityDto;
import com.epam.rd.edu.petProject.service.CityDtoServiceInterface;
import com.epam.rd.edu.petProject.service.SimpleServiceFactory;
import com.epam.rd.edu.petProject.web.command.AbstractFrontCommand;

import javax.servlet.http.HttpSession;
import java.util.List;

public class GetAllCitiesCommand extends AbstractFrontCommand {
    private final CityDtoServiceInterface cityDtoService = SimpleServiceFactory.getFactory().getCityService();

    @Override
    public void progress() {
        List<CityDto> cityDtoList = cityDtoService.getAll();
        HttpSession session = request.getSession();
        session.setAttribute("citiesList", cityDtoList);
        forward(pagePathManager.getPath("getAllCitiesPage"));
    }
}