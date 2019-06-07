package com.gmail.derynem.finalcontrolwork.service.converter.impl;

import com.gmail.derynem.finalcontrolwork.repository.model.Discount;
import com.gmail.derynem.finalcontrolwork.repository.model.User;
import com.gmail.derynem.finalcontrolwork.service.converter.Converter;
import com.gmail.derynem.finalcontrolwork.service.model.DiscountDTO;
import com.gmail.derynem.finalcontrolwork.service.model.UserDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("userConverter")
public class UserConverterImpl implements Converter<UserDTO, User> {
    private final Converter<DiscountDTO, Discount> discountConverter;

    public UserConverterImpl(@Qualifier("discountConverter") Converter<DiscountDTO, Discount> discountConverter) {
        this.discountConverter = discountConverter;
    }

    @Override
    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setDeleted(user.isDeleted());
        userDTO.setId(user.getId());
        userDTO.setPassword(user.getPassword());
        userDTO.setUserName(user.getUserName());
        userDTO.setDiscounts(getListOfDiscountDTO(user.getDiscounts()));
        userDTO.setRole(user.getRole().name());
        return userDTO;
    }


    @Override
    public User toEntity(UserDTO ObjectDTO) {
        throw new UnsupportedOperationException();
    }


    private List<DiscountDTO> getListOfDiscountDTO(List<Discount> discounts) {
        return discounts.stream()
                .map(discountConverter::toDTO)
                .collect(Collectors.toList());
    }
}