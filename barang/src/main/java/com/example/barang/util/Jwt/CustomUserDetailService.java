package com.example.barang.util.Jwt;

import com.example.barang.persistence.domain.OrderTrans;
import com.example.barang.persistence.repository.OrderTransRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomUserDetailService  implements UserDetailsService
{
    private static final Logger logger = LogManager.getLogger(CustomUserDetailService.class);
    @Autowired
    private OrderTransRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<OrderTrans> checkUser=  repository.findByEmail(username);
        if(checkUser.isEmpty())
        {
            logger.info("users for auth not found");
            throw new UsernameNotFoundException("user not found");
        }
        return new org.springframework.security.core.userdetails.User(checkUser.get(0).getEmail()
                ,checkUser.get(0).getOrderId(),new ArrayList<>());
    }
}
