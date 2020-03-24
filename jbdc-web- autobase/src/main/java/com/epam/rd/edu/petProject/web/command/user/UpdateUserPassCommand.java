package com.epam.rd.edu.petProject.web.command.user;

import com.epam.rd.edu.petProject.dto.UserDto;
import com.epam.rd.edu.petProject.service.SimpleServiceFactory;
import com.epam.rd.edu.petProject.service.UserDtoServiceInterface;
import com.epam.rd.edu.petProject.utils.HashUtil;
import com.epam.rd.edu.petProject.web.command.AbstractFrontCommand;

public class UpdateUserPassCommand extends AbstractFrontCommand {
    private final UserDtoServiceInterface userDtoService = SimpleServiceFactory.getFactory().getUserService();

    @Override
    public void progress() {
        UserDto userDto = userDtoService.read(Integer.parseInt(request.getParameter("user_id")));
        userDto.setPassword(HashUtil.messageToHash(userDto.getPassword()));
        userDtoService.update(userDto);
        forward(pagePathManager.getPath("getAllUsersPage"));
    }
}

