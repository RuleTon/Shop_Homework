package com.example.securityhomework.contollers;

import com.example.securityhomework.entities.User;
import com.example.securityhomework.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@Profile("dao")
@Slf4j
@RequiredArgsConstructor
public class DaoController {
    private final UserService userService;

    @GetMapping("/dao")
    public String daoTestPage(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("Can't find user: " + principal.getName()));
        return "auth: " + user.getUsername() + " : " + user.getEmail();
    }


}
