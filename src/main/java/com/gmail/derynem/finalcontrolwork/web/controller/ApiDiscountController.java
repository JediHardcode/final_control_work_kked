package com.gmail.derynem.finalcontrolwork.web.controller;

import com.gmail.derynem.finalcontrolwork.service.DiscountService;
import com.gmail.derynem.finalcontrolwork.service.exception.DiscountServiceException;
import com.gmail.derynem.finalcontrolwork.service.model.DiscountDTO;
import com.gmail.derynem.finalcontrolwork.service.model.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ApiDiscountController {
    private final static Logger logger = LoggerFactory.getLogger(ApiDiscountController.class);
    private final DiscountService discountService;

    public ApiDiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @PostMapping("/root/discount")
    public ResponseEntity addDiscount(@RequestBody @Valid DiscountDTO discountDTO,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.info(" discount not valid cause {}", Arrays.toString(bindingResult.getAllErrors().toArray()));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        discountService.add(discountDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/discounts")
    public ResponseEntity<List<DiscountDTO>> getListOfUserDiscount(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        List<DiscountDTO> discounts = discountService.getUserDiscounts(userPrincipal.getUser());
        return new ResponseEntity<>(discounts, HttpStatus.OK);
    }

    @DeleteMapping("/root/discounts/{id}")
    public ResponseEntity deleteDiscount(@PathVariable(name = "id") Long id) {
        try {
            discountService.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (DiscountServiceException e) {
            logger.error(e.getMessage(), e);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}