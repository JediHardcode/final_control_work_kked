package com.gmail.derynem.finalcontrolwork.service.impl;

import com.gmail.derynem.finalcontrolwork.repository.DiscountRepository;
import com.gmail.derynem.finalcontrolwork.repository.UserRepository;
import com.gmail.derynem.finalcontrolwork.repository.model.Discount;
import com.gmail.derynem.finalcontrolwork.repository.model.User;
import com.gmail.derynem.finalcontrolwork.service.DiscountService;
import com.gmail.derynem.finalcontrolwork.service.converter.Converter;
import com.gmail.derynem.finalcontrolwork.service.exception.DiscountServiceException;
import com.gmail.derynem.finalcontrolwork.service.model.DiscountDTO;
import com.gmail.derynem.finalcontrolwork.service.model.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscountServiceImpl implements DiscountService {
    private final static Logger logger = LoggerFactory.getLogger(DiscountServiceImpl.class);
    private final DiscountRepository discountRepository;
    private final Converter<DiscountDTO, Discount> discountConverter;
    private final UserRepository userRepository;

    public DiscountServiceImpl(DiscountRepository discountRepository,
                               @Qualifier("discountConverter") Converter<DiscountDTO, Discount> discountConverter,
                               UserRepository userRepository) {
        this.discountRepository = discountRepository;
        this.discountConverter = discountConverter;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void add(DiscountDTO discountDTO) {
        Discount discount = discountConverter.toEntity(discountDTO);
        User user = userRepository.getById(discountDTO.getUser().getId());
        discount.setUser(user);
        discountRepository.persist(discount);
        logger.info("discount with name{} for user {} successfully saved", discount.getName(), user.getUserName());
    }

    @Override
    @Transactional
    public List<DiscountDTO> getUserDiscounts(UserDTO user) {
        User foundUser = userRepository.getById(user.getId());
        List<DiscountDTO> discountDTOS = getDiscounts(foundUser.getDiscounts());
        logger.info("  user with name {} have {} discounts", foundUser.getUserName(), discountDTOS.size());
        return discountDTOS;
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws DiscountServiceException {
        Discount discount = discountRepository.getById(id);
        if (discount == null) {
            logger.info("discount with id {} doesnt exist in database ", id);
            throw new DiscountServiceException("discount not found");
        }
        discountRepository.remove(discount);
        logger.info("discount with id {} deleted", discount.getId());
    }

    private List<DiscountDTO> getDiscounts(List<Discount> discounts) {
        return discounts.stream()
                .map(discountConverter::toDTO)
                .collect(Collectors.toList());
    }
}