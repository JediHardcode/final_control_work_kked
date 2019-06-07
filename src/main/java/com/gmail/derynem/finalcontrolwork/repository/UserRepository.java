package com.gmail.derynem.finalcontrolwork.repository;

import com.gmail.derynem.finalcontrolwork.repository.model.User;

public interface UserRepository extends GenericRepository<Long, User> {
    User getByName(String name);
}