package com.epam.rd.edu.petProject.web.command.order;

import com.epam.rd.edu.petProject.dto.CityDto;
import com.epam.rd.edu.petProject.dto.OrderDto;
import com.epam.rd.edu.petProject.dto.UserDto;
import com.epam.rd.edu.petProject.model.Car;
import com.epam.rd.edu.petProject.service.CityDtoServiceInterface;
import com.epam.rd.edu.petProject.service.OrderDtoServiceInterface;
import com.epam.rd.edu.petProject.service.SimpleServiceFactory;
import com.epam.rd.edu.petProject.service.UserDtoServiceInterface;
import com.epam.rd.edu.petProject.web.command.AbstractFrontCommand;

import javax.servlet.http.HttpSession;

public class NewOrderCommand extends AbstractFrontCommand {
    private final OrderDtoServiceInterface orderDtoService = SimpleServiceFactory.getFactory().getOrderService();
    private final CityDtoServiceInterface cityDtoService = SimpleServiceFactory.getFactory().getCityService();
    private final UserDtoServiceInterface userDtoService = SimpleServiceFactory.getFactory().getUserService();

    @Override
    public void progress() {
        OrderDto orderDto = getOrderDto();
        orderDtoService.create(orderDto);
        forward(pagePathManager.getPath("getAllOrdersPage"));
    }

    private OrderDto getOrderDto() {
        return OrderDto.builder()
                .user(getUserDto())
                .city_from(getCityFromDto())
                .city_to(getCityToDto())
                .carModel(Car.CarModel.valueOf(request.getParameter("carModel")))
                .build();
    }

    private UserDto getUserDto() {
        HttpSession session = request.getSession();
        return userDtoService.getByLogin(String.valueOf(session.getAttribute("login")));
    }

    private CityDto getCityFromDto() {
        return cityDtoService.read(Integer.parseInt(request.getParameter("city_from")));
    }

    private CityDto getCityToDto() {
        return cityDtoService.read(Integer.parseInt(request.getParameter("city_to")));
    }
}

