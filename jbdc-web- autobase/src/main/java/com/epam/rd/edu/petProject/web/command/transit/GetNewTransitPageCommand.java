package com.epam.rd.edu.petProject.web.command.transit;

import com.epam.rd.edu.petProject.dto.CarDto;
import com.epam.rd.edu.petProject.dto.CityDto;
import com.epam.rd.edu.petProject.dto.UserDto;
import com.epam.rd.edu.petProject.model.Transit;
import com.epam.rd.edu.petProject.model.User;
import com.epam.rd.edu.petProject.service.CarDtoServiceInterface;
import com.epam.rd.edu.petProject.service.CityDtoServiceInterface;
import com.epam.rd.edu.petProject.service.SimpleServiceFactory;
import com.epam.rd.edu.petProject.service.UserDtoServiceInterface;
import com.epam.rd.edu.petProject.web.command.AbstractFrontCommand;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetNewTransitPageCommand extends AbstractFrontCommand {
    private final CityDtoServiceInterface cityDtoService = SimpleServiceFactory.getFactory().getCityService();
    private final CarDtoServiceInterface carDtoService = SimpleServiceFactory.getFactory().getCarService();
    private final UserDtoServiceInterface userDtoService = SimpleServiceFactory.getFactory().getUserService();

    @Override
    public void progress() {
        List<Transit.Status> statusList = Arrays.asList(Transit.Status.values());
        List<CarDto> carList = carDtoService.getAll();
        List<CityDto> cityList = cityDtoService.getAll();
        List<UserDto> driverDtoList = userDtoService.getAll();

        HttpSession session = request.getSession();
        session.setAttribute("statusList", statusList);
        session.setAttribute("carList", carList);
        session.setAttribute("cityList", cityList);
        session.setAttribute("cityList", getOnlyDrivers(driverDtoList));
        forward(pagePathManager.getPath("createTransitPage"));
    }

    private List getOnlyDrivers(List<UserDto> userDtoList) {
        List<UserDto> driverList = new ArrayList<>();
        for (UserDto userDto : userDtoList) {
            if (userDto.getRole() == User.Role.DRIVER) {
                driverList.add(userDto);
            }
        }
        return driverList;
    }
}
