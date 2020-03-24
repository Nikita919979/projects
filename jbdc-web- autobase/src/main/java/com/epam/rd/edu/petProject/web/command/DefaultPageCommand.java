package com.epam.rd.edu.petProject.web.command;

public class DefaultPageCommand extends AbstractFrontCommand {

    @Override
    public void progress() {
        forward(pagePathManager.getPath("startPage"));
    }
}

