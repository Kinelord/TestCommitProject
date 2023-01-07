package com.shakirov.http.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author igor@shakirov-dev.ru on 26.12.2022
 * @version 1.0
 */

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "user/login";
    }

    // Spring Security сам будет перенаправлять нас на страницу аутентификации
/*    @PostMapping("/login")
    public String login(@ModelAttribute("login") LoginDto loginDto, Model model) {
//        return "forward:/WEB-INF/user/login";
        return "redirect:https://google.com";
    }*/
}
