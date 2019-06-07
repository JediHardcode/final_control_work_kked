package com.gmail.derynem.finalcontrolwork.repository.impl;

import com.gmail.derynem.finalcontrolwork.repository.DiscountRepository;
import com.gmail.derynem.finalcontrolwork.repository.model.Discount;
import org.springframework.stereotype.Repository;

@Repository
public class DiscountRepositoryImpl extends GenericRepositoryImpl<Long, Discount> implements DiscountRepository {
}