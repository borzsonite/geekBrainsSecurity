package edu.springsecurity.demo.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class MainController {
    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/authenticated")
    public String pageForAuthenticatedUsers(Principal principal) {
        return "Page for authenticated users: " + principal.getName();
    }

    @GetMapping("/admin")
    public String pageForAdmins() {
        return "Admin's page";
    }
}
