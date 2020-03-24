package com.epam.rd.edu.petProject.web.command.car;

import com.epam.rd.edu.petProject.dto.CarDto;
import com.epam.rd.edu.petProject.model.Car;
import com.epam.rd.edu.petProject.service.CarDtoServiceInterface;
import com.epam.rd.edu.petProject.service.SimpleServiceFactory;
import com.epam.rd.edu.petProject.web.command.AbstractFrontCommand;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NewCarCommand extends AbstractFrontCommand {
    private final CarDtoServiceInterface carDtoService = SimpleServiceFactory.getFactory().getCarService();

    @Override
    public void progress() {
        CarDto carDto = getCarDto();
        carDtoService.create(carDto);
        forward(pagePathManager.getPath("getAllCarsPage"));
    }

    private CarDto getCarDto() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return CarDto.builder()
                .carNumber(request.getParameter("number"))
                .model(Car.CarModel.valueOf(request.getParameter("model")))
                .carTechnicalPassport(request.getParameter("technicalPassport"))
                .releaseDate(LocalDate.parse(request.getParameter("releaseDate"), format))
                .fully_Functional(getFully_Functional())
                .build();
    }

    private boolean getFully_Functional() {
        if (request.getParameter("fully_Functional") == null) {
            return false;
        } else {
            return true;
        }
    }
}

