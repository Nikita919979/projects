package com.epam.rd.edu.petProject.web.command.car;

import com.epam.rd.edu.petProject.dto.CarDto;
import com.epam.rd.edu.petProject.model.Car;
import com.epam.rd.edu.petProject.model.User;
import com.epam.rd.edu.petProject.service.CarDtoServiceInterface;
import com.epam.rd.edu.petProject.service.SimpleServiceFactory;
import com.epam.rd.edu.petProject.web.command.AbstractFrontCommand;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UpdateCarCommand extends AbstractFrontCommand {
    private final CarDtoServiceInterface carDtoService = SimpleServiceFactory.getFactory().getCarService();

    @Override
    public void progress() {
        HttpSession session = request.getSession();
        CarDto carDto = null;
        if (User.Role.DISPATCHER.equals(session.getAttribute("role"))) {
            carDto = carDtoService.read(Integer.parseInt(request.getParameter("car_id")));
            carDto.setFully_Functional(getFully_Functional());
        } else {
            carDto = getCarDto();
        }
        carDtoService.update(carDto);
        forward(pagePathManager.getPath("getAllCarsPage"));
    }

    private CarDto getCarDto() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return CarDto.builder()
                .id(Integer.parseInt(request.getParameter("car_id")))
                .fully_Functional(getFully_Functional())
                .model(Car.CarModel.valueOf(request.getParameter("model")))
                .releaseDate(LocalDate.parse(request.getParameter("releaseDate"), format))
                .carTechnicalPassport(request.getParameter("carTechnicalPassport"))
                .carNumber(request.getParameter("carNumber"))
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

