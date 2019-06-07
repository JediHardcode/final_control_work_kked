package com.gmail.derynem.finalcontrolwork.service.impl;


import com.gmail.derynem.finalcontrolwork.service.UserService;
import com.gmail.derynem.finalcontrolwork.service.model.UserDTO;
import com.gmail.derynem.finalcontrolwork.service.model.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class AppUserDetailsService implements UserDetailsService {
    private final static Logger logger = LoggerFactory.getLogger(AppUserDetailsService.class);
    private final UserService userService;

    public AppUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        UserDTO foundUser = userService.getByName(name);
        if (foundUser == null) {
            logger.info("Not found user in database with this email: {}", name);
            throw new UsernameNotFoundException("Not found user in database with this email:" + name);
        }
        return new UserPrincipal(foundUser);
    }
}