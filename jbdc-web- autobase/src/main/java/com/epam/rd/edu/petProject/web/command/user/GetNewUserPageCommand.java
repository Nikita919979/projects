package com.epam.rd.edu.petProject.web.command.user;

import com.epam.rd.edu.petProject.model.User;
import com.epam.rd.edu.petProject.web.command.AbstractFrontCommand;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

public class GetNewUserPageCommand extends AbstractFrontCommand {
    @Override
    public void progress() {
        List<User.Role> userList = Arrays.asList(User.Role.values());
        HttpSession session = request.getSession();
        session.setAttribute("roleList", userList);
        forward(pagePathManager.getPath("createUserPage"));
    }
}

