package com.oxd.parkingcontrol.configs.security;

import com.oxd.parkingcontrol.models.UserModel;
import com.oxd.parkingcontrol.repositories.UserRespository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class UserDetailServiceImpl implements UserDetailsService {

    final UserRespository userRespository;

    public UserDetailServiceImpl(UserRespository userRespository) {
        this.userRespository = userRespository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = userRespository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found with username: "+ username)
        );

        return new User(userModel.getUsername(), userModel.getPassword(), true, true, true, true, userModel.getAuthorities());
    }
}
