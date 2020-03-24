package com.epam.rd.edu.petProject.web.command.order;

import com.epam.rd.edu.petProject.dto.CarDto;
import com.epam.rd.edu.petProject.dto.CityDto;
import com.epam.rd.edu.petProject.model.Car;
import com.epam.rd.edu.petProject.service.CarDtoServiceInterface;
import com.epam.rd.edu.petProject.service.CityDtoServiceInterface;
import com.epam.rd.edu.petProject.service.SimpleServiceFactory;
import com.epam.rd.edu.petProject.web.command.AbstractFrontCommand;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

public class GetNewOrderPageCommand extends AbstractFrontCommand {
    private final CityDtoServiceInterface cityDtoService = SimpleServiceFactory.getFactory().getCityService();
    private final CarDtoServiceInterface carDtoService = SimpleServiceFactory.getFactory().getCarService();

    @Override
    public void progress() {
        List<Car.CarModel> carModels = Arrays.asList(Car.CarModel.values());
        List<CarDto> carList = carDtoService.getAll();
        List<CityDto> cityList = cityDtoService.getAll();

        HttpSession session = request.getSession();
        session.setAttribute("carModels", carModels);
        session.setAttribute("carList", carList);
        session.setAttribute("cityList", cityList);
        forward(pagePathManager.getPath("createOrderPage"));
    }
}
