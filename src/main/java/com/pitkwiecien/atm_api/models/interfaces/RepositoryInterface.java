package com.pitkwiecien.atm_api.models.interfaces;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public interface RepositoryInterface<T> {
    String addObject(T obj);
    List<T> getObjects();
    T getObjectByKey(String key);
}
