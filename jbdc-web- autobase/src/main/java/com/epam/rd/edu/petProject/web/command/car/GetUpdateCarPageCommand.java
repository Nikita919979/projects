package com.epam.rd.edu.petProject.web.command.car;

import com.epam.rd.edu.petProject.dto.CarDto;
import com.epam.rd.edu.petProject.model.Car;
import com.epam.rd.edu.petProject.service.CarDtoServiceInterface;
import com.epam.rd.edu.petProject.service.SimpleServiceFactory;
import com.epam.rd.edu.petProject.web.command.AbstractFrontCommand;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

public class GetUpdateCarPageCommand extends AbstractFrontCommand {
    private final CarDtoServiceInterface carDtoService = SimpleServiceFactory.getFactory().getCarService();

    @Override
    public void progress() {
        HttpSession session = request.getSession();
        List<Car.CarModel> modelList = Arrays.asList(Car.CarModel.values());
        CarDto carDto = carDtoService.read(Integer.parseInt(request.getParameter("car_id")));
        session.setAttribute("carDto", carDto);
        session.setAttribute("modelList", modelList);
        forward(pagePathManager.getPath("updateCarPage"));
    }
}

