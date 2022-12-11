package com.pitkwiecien.atm_api.models.interfaces;

import java.util.List;

public interface RepositoryInterface<T> {
    int addObject(T obj);
    List<T> getObjects();
    T getObjectByKey(String key);
    void setRandomKey(T obj);
}
