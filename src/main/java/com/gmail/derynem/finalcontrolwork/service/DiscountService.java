package com.gmail.derynem.finalcontrolwork.service;

import com.gmail.derynem.finalcontrolwork.service.exception.DiscountServiceException;
import com.gmail.derynem.finalcontrolwork.service.model.DiscountDTO;
import com.gmail.derynem.finalcontrolwork.service.model.UserDTO;

import java.util.List;

public interface DiscountService {
    void add(DiscountDTO discountDTO);

    List<DiscountDTO> getUserDiscounts(UserDTO user);

    void deleteById(Long id) throws DiscountServiceException;
}