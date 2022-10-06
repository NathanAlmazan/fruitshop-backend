package com.nathanael.fruitshop.users;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class AccountDetailService implements UserDetailsService {
    @Autowired private UserServices userServices;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount account = userServices.getUserByUsername(username);
        if (account == null) throw new UsernameNotFoundException("There is no account connected to " + username);
        else if (account.getPassword() == null) throw new UsernameNotFoundException("There is no account connected to " + username);

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(account.getUserPosition().toString()));

        return new User(account.getEmail(), account.getPassword(), authorities);
    }
}
