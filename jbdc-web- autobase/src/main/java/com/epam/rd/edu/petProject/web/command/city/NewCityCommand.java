package com.epam.rd.edu.petProject.web.command.city;

import com.epam.rd.edu.petProject.dto.CityDto;
import com.epam.rd.edu.petProject.service.CityDtoServiceInterface;
import com.epam.rd.edu.petProject.service.SimpleServiceFactory;
import com.epam.rd.edu.petProject.web.command.AbstractFrontCommand;

public class NewCityCommand extends AbstractFrontCommand {
    private final CityDtoServiceInterface cityDtoService = SimpleServiceFactory.getFactory().getCityService();

    @Override
    public void progress() {
        CityDto cityDto = getCityDto();
        cityDtoService.create(cityDto);
        forward(pagePathManager.getPath("getAllCitiesPage"));
    }

    private CityDto getCityDto() {
        return CityDto.builder()
                .name(request.getParameter("name"))
                .build();
    }
}

