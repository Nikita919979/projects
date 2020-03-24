package com.epam.rd.edu.petProject.web.command.order;

import com.epam.rd.edu.petProject.dto.CityDto;
import com.epam.rd.edu.petProject.dto.OrderDto;
import com.epam.rd.edu.petProject.model.Car;
import com.epam.rd.edu.petProject.service.CityDtoServiceInterface;
import com.epam.rd.edu.petProject.service.OrderDtoServiceInterface;
import com.epam.rd.edu.petProject.service.SimpleServiceFactory;
import com.epam.rd.edu.petProject.web.command.AbstractFrontCommand;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

public class GetUpdateOrderPageCommand extends AbstractFrontCommand {
    private final CityDtoServiceInterface cityDtoService = SimpleServiceFactory.getFactory().getCityService();
    private final OrderDtoServiceInterface orderDtoService = SimpleServiceFactory.getFactory().getOrderService();

    @Override
    public void progress() {
        HttpSession session = request.getSession();
        List<Car.CarModel> carModels = Arrays.asList(Car.CarModel.values());
        OrderDto orderDto = orderDtoService.read(Integer.parseInt(request.getParameter("order_id")));
        List<CityDto> cityDtoList = cityDtoService.getAll();
        session.setAttribute("cityDtoList", cityDtoList);
        session.setAttribute("orderDto", orderDto);
        session.setAttribute("carModels", carModels);
        forward(pagePathManager.getPath("updateOrderPage"));
    }
}

