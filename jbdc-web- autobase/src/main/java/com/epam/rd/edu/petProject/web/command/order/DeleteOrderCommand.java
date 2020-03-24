package com.epam.rd.edu.petProject.web.command.order;

import com.epam.rd.edu.petProject.service.OrderDtoServiceInterface;
import com.epam.rd.edu.petProject.service.SimpleServiceFactory;
import com.epam.rd.edu.petProject.web.command.AbstractFrontCommand;

public class DeleteOrderCommand extends AbstractFrontCommand {
    private final OrderDtoServiceInterface orderDtoService = SimpleServiceFactory.getFactory().getOrderService();

    @Override
    public void progress() {
        orderDtoService.delete(Integer.parseInt(request.getParameter("order_id")));
        forward(pagePathManager.getPath("getAllOrdersPage"));
    }
}

