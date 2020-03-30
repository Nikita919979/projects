package com.epam.rd.edu.petproject.model;

import java.io.Serializable;

public abstract class AbstractEntity<T extends Serializable> implements Cloneable {

  public abstract T getUuid();

  public abstract void setUuid(T uuid);

  public abstract AbstractEntity clone();
}
