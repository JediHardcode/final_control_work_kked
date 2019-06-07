package com.gmail.derynem.finalcontrolwork.service.converter.impl;

import com.gmail.derynem.finalcontrolwork.repository.model.Discount;
import com.gmail.derynem.finalcontrolwork.service.converter.Converter;
import com.gmail.derynem.finalcontrolwork.service.model.DiscountDTO;
import org.springframework.stereotype.Component;

@Component("discountConverter")
public class DiscountConverterImpl implements Converter<DiscountDTO, Discount> {
    @Override
    public DiscountDTO toDTO(Discount discount) {
        DiscountDTO discountDTO = new DiscountDTO();
        discountDTO.setPercent(discount.getPercent());
        discountDTO.setName(discount.getName());
        discountDTO.setId(discount.getId());
        discountDTO.setDate(discount.getDate());
        return discountDTO;
    }

    @Override
    public Discount toEntity(DiscountDTO discountDTO) {
        Discount discount = new Discount();
        discount.setDate(discountDTO.getDate());
        discount.setName(discountDTO.getName());
        discount.setPercent(discountDTO.getPercent());
        return discount;
    }
}