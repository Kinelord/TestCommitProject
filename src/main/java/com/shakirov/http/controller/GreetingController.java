package com.shakirov.http.controller;

import com.shakirov.dto.UserReadDto;
import com.shakirov.model.Role;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;


@Tag(name = "Test Rest контроллер API", description = "Test Api для работы с приложением")
@Controller
@RequestMapping("/api")
@SessionAttributes({"user", "session"})
public class GreetingController {


    @GetMapping("/hello")
    public String hello(Model model, HttpServletRequest request,
//                        @ModelAttribute // Эту аннотацию можно опустить, если мы не хотим изменить
//                        дефолтные настройки (Например name = "userReadDto" по умолчанию имя класса)
                        @ModelAttribute(name = "userReadDtoMA") UserReadDto userDto) {

        model.addAttribute("user", userDto);

        return "greeting/hello";
    }

    @GetMapping("/buy")
    public String bye(@SessionAttribute("user") UserReadDto user, Model model) {

        return "greeting/buy";
    }

    @ModelAttribute(name = "roles")
    public List<Role> roles() {
        return Arrays.asList(Role.values());
    }

    @GetMapping("/hellomav")
    public ModelAndView hellomav(ModelAndView modelAndView, HttpServletRequest request) {

        // Сделаем все аттрибуты сессионными с помощью @SessionAttributes
/*//        Session Scope
        request.getSession().setAttribute("session", new UserReadDto(100L, "Session username Igor"));
//        Request Scope
        request.setAttribute("request", new UserReadDto(10L, "Request username Igor"));*/

//        request.getSession().getAttribute("user");  // Это будет делать Спринг из метода buy, так как его
//        об этом просим @SessionAttribute("user")
        modelAndView.setViewName("greeting/hello");
        // .addObject() - использует по умолчанию способ задать аттрибут на RequestAttribute, изменим это с помощью @SessionAttributes
//        modelAndView.addObject("user", new UserReadDto(1L, "Igor"));

        return modelAndView;
    }

    @GetMapping("/buymav")
    public ModelAndView byemav(@SessionAttribute("user") UserReadDto user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("greeting/buy");

        return modelAndView;
    }


    @GetMapping("/hello/{id}")
    public ModelAndView helloid(ModelAndView modelAndView, HttpServletRequest request,
                                @RequestParam(required = false) Integer age,
                                @RequestHeader String accept,
                                @CookieValue("JSESSIONID") String JSESSIONID,
                                @PathVariable("id") Integer id) {
        String ageParamValue = request.getParameter("age");
        String acceptHeader = request.getHeader("accept");
        Cookie[] cookies = request.getCookies();

        modelAndView.setViewName("greeting/hello");

        return modelAndView;
    }

}