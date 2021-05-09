package com.league.service;

import java.util.Set;

public interface BaseService<T,ID>
{
    Set<T> findAll();

    T save(T t);

    T update(ID id,T t);

    T findById(ID id);

    void deleteById(ID id);
}
