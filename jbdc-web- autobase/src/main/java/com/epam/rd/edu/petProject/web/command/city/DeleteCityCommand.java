package com.epam.rd.edu.petProject.web.command.city;

import com.epam.rd.edu.petProject.service.CityDtoServiceInterface;
import com.epam.rd.edu.petProject.service.SimpleServiceFactory;
import com.epam.rd.edu.petProject.web.command.AbstractFrontCommand;

public class DeleteCityCommand extends AbstractFrontCommand {
    private final CityDtoServiceInterface cityDtoService = SimpleServiceFactory.getFactory().getCityService();

    @Override
    public void progress() {
        cityDtoService.delete(Integer.parseInt(request.getParameter("city_id")));
        forward(pagePathManager.getPath("getAllCitiesPage"));
    }
}

