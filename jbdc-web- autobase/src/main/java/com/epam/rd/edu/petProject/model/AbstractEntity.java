package com.epam.rd.edu.petProject.model;

import java.io.Serializable;

public abstract class AbstractEntity<T extends Serializable> implements Cloneable {

    public abstract void setId(T id);

    public abstract T getId();

    public abstract AbstractEntity clone();
}
