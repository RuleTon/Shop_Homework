package com.example.securityhomework.contollers;

import com.example.securityhomework.entities.User;
import com.example.securityhomework.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class MainContoller {

    @GetMapping("/admin")
    public String adminPage()
        {
            return "admin";
        }

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/unsecured")
    public String unsecuredPage() {
        return "unsecured";
    }

    @GetMapping("/auth_page")
    public String authPage() {
        return "authenticated";
    }


    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return UserService.getById(id);
    }

    @GetMapping("/inc")
    public String incScore (@PathParam("{/score}") int score){
        return "Increased Score" + incScore(score); }

    @GetMapping("/dec")
    public String decScore (@PathParam("{/score}") int score) {
        return "Decreased Score" + decScore(score); }

    @GetMapping("/current")
    public String curScore (@PathParam("{/score}") int score) {
        return "Current Score" + score; }





}
