package com.epam.rd.edu.petProject.web.command.transit;

import com.epam.rd.edu.petProject.service.SimpleServiceFactory;
import com.epam.rd.edu.petProject.service.TransitDtoServiceInterface;
import com.epam.rd.edu.petProject.web.command.AbstractFrontCommand;

public class DeleteTransitCommand extends AbstractFrontCommand {
    private final TransitDtoServiceInterface transitDtoService = SimpleServiceFactory.getFactory().getTransitService();

    @Override
    public void progress() {
        transitDtoService.delete(Integer.parseInt(request.getParameter("transit_id")));
        forward(pagePathManager.getPath("getAllTransitsPage"));
    }
}

