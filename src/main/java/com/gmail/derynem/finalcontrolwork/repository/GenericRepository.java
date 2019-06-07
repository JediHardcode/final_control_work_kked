package com.gmail.derynem.finalcontrolwork.repository;

import java.util.List;

public interface GenericRepository<I, T> {
    void persist(T entity);

    void remove(T entity);

    T getById(I id);

    @SuppressWarnings({"unchecked", "rawtypes"})
    List<T> findAll(int offset, int limit);
}