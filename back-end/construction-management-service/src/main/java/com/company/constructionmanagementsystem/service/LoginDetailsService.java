package com.company.constructionmanagementsystem.service;

import com.company.constructionmanagementsystem.viewmodel.EmployeeViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class LoginDetailsService implements UserDetailsService {
    @Autowired
    EmployeeServiceLayer employeeServiceLayer;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EmployeeViewModel employeeViewModel = employeeServiceLayer.findEmployeeByUsername(username);
        List<GrantedAuthority> authorities = getEmployeeAuthority(employeeViewModel.getTitle());
        return buildUserForAuthentication(employeeViewModel, authorities);
    }

    private List<GrantedAuthority> getEmployeeAuthority(String title) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(title));
        return grantedAuthorities;
    }

    private UserDetails buildUserForAuthentication(EmployeeViewModel user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                true, true, true, true, authorities);
    }
}

