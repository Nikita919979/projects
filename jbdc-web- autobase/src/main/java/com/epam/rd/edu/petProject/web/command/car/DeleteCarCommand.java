package com.epam.rd.edu.petProject.web.command.car;

import com.epam.rd.edu.petProject.service.CarDtoServiceInterface;
import com.epam.rd.edu.petProject.service.SimpleServiceFactory;
import com.epam.rd.edu.petProject.web.command.AbstractFrontCommand;

public class DeleteCarCommand extends AbstractFrontCommand {
    private final CarDtoServiceInterface carDtoService = SimpleServiceFactory.getFactory().getCarService();

    @Override
    public void progress() {
        carDtoService.delete(Integer.parseInt(request.getParameter("car_id")));
        forward(pagePathManager.getPath("getAllCarsPage"));
    }
}

