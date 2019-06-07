package com.gmail.derynem.finalcontrolwork.repository.impl;

import com.gmail.derynem.finalcontrolwork.repository.UserRepository;
import com.gmail.derynem.finalcontrolwork.repository.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;

@Repository
public class UserRepositoryImpl extends GenericRepositoryImpl<Long, User> implements UserRepository {
    private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);

    @Override
    public User getByName(String name) {
        String stringQuery = "select e from " + entityClass.getName() + " e where e.userName = :name";
        Query query = entityManager.createQuery(stringQuery);
        query.setParameter("name", name);
        try {
            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            logger.info(" user with name {} not found in database", name);
            return null;
        }
    }
}