package com.epam.rd.edu.petProject.web.command.car;

import com.epam.rd.edu.petProject.model.Car;
import com.epam.rd.edu.petProject.web.command.AbstractFrontCommand;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

public class GetNewCarPageCommand extends AbstractFrontCommand {
    @Override
    public void progress() {
        List<Car.CarModel> modelList = Arrays.asList(Car.CarModel.values());
        HttpSession session = request.getSession();
        session.setAttribute("modelList", modelList);
        forward(pagePathManager.getPath("createCarPage"));
    }
}
