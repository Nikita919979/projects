package com.epam.rd.edu.petProject.web.command.order;

import com.epam.rd.edu.petProject.dto.OrderDto;
import com.epam.rd.edu.petProject.model.User;
import com.epam.rd.edu.petProject.service.OrderDtoServiceInterface;
import com.epam.rd.edu.petProject.service.SimpleServiceFactory;
import com.epam.rd.edu.petProject.web.command.AbstractFrontCommand;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

public class GetAllOrdersCommand extends AbstractFrontCommand {
    private final OrderDtoServiceInterface orderDtoService = SimpleServiceFactory.getFactory().getOrderService();

    @Override
    public void progress() {
        HttpSession session = request.getSession();
        List<OrderDto> orderDtoList = orderDtoService.getAll();
        if (User.Role.DRIVER.equals(session.getAttribute("role"))) {
            String login = (String) session.getAttribute("login");
            orderDtoList = orderDtoList.stream().filter(orderDto -> (orderDto.getUser().getLogin().equals(login)))
                    .collect(Collectors.toList());
        }
        session.setAttribute("ordersList", orderDtoList);
        forward(pagePathManager.getPath("getAllOrdersPage"));
    }
}