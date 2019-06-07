package com.gmail.derynem.finalcontrolwork.service.impl;

import com.gmail.derynem.finalcontrolwork.repository.UserRepository;
import com.gmail.derynem.finalcontrolwork.repository.model.User;
import com.gmail.derynem.finalcontrolwork.service.UserService;
import com.gmail.derynem.finalcontrolwork.service.converter.Converter;
import com.gmail.derynem.finalcontrolwork.service.model.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.gmail.derynem.finalcontrolwork.service.constant.RequestParam.DEFAULT_LIMIT;
import static com.gmail.derynem.finalcontrolwork.service.constant.RequestParam.DEFAULT_OFFSET;

@Service
public class UserServiceImpl implements UserService {
    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final Converter<UserDTO, User> userConverter;

    public UserServiceImpl(UserRepository userRepository,
                           @Qualifier("userConverter") Converter<UserDTO, User> userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    @Transactional
    public UserDTO getByName(String name) {
        User user = userRepository.getByName(name);
        if (user == null) {
            logger.info("user with name {} not fount in database", name);
            return null;
        }
        logger.info("user with name {} found", name);
        return userConverter.toDTO(user);
    }

    @Override
    @Transactional
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll(DEFAULT_LIMIT, DEFAULT_OFFSET);
        List<UserDTO> userDTOS = getUsersDTO(users);
        logger.info("count of users is {}", userDTOS.size());
        return userDTOS;
    }

    private List<UserDTO> getUsersDTO(List<User> users) {
        return users.stream()
                .map(userConverter::toDTO)
                .collect(Collectors.toList());
    }
}