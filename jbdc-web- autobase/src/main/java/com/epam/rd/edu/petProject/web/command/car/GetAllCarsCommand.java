package com.epam.rd.edu.petProject.web.command.car;

import com.epam.rd.edu.petProject.dto.CarDto;
import com.epam.rd.edu.petProject.service.CarDtoServiceInterface;
import com.epam.rd.edu.petProject.service.SimpleServiceFactory;
import com.epam.rd.edu.petProject.web.command.AbstractFrontCommand;

import javax.servlet.http.HttpSession;
import java.util.List;

public class GetAllCarsCommand extends AbstractFrontCommand {
    private final CarDtoServiceInterface carDtoService = SimpleServiceFactory.getFactory().getCarService();

    @Override
    public void progress() {
        List<CarDto> carDtoList = carDtoService.getAll();
        HttpSession session = request.getSession();
        session.setAttribute("carsList", carDtoList);
        forward(pagePathManager.getPath("getAllCarsPage"));
    }
}