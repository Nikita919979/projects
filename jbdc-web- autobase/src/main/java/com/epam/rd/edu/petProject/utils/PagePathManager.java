package com.epam.rd.edu.petProject.utils;

import java.util.ResourceBundle;

public class PagePathManager {
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("pagesPath");

    public static PagePathManager getInstance() {
        return new PagePathManager();
    }

    public String getPath(String property) {
        return resourceBundle.getString(property);
    }
}
