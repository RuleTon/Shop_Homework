package com.example.securityhomework.services;


import com.example.securityhomework.entities.Role;
import com.example.securityhomework.entities.User;
import com.example.securityhomework.repositories.RoleRepository;
import com.example.securityhomework.repositories.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Profile("dao")
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public static User getById(Long id) {
       return UserRepository.findById(id).get();
   }






    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User" + username + "not found")));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }






}
