package com.github.bruce.controller;

import com.github.bruce.dao.entity.UserEntity;
import com.github.bruce.model.dto.UserDto;
import com.github.bruce.service.RegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
public class RegisterController {

    @Resource
    private RegisterService service;

    @GetMapping(value = "/registration")
    public String showRegistrationForm(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @PostMapping("/registration")
    public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid UserDto userDto,
                                            BindingResult result, Errors errors) {
        UserEntity registered;
        if (result.hasErrors()) {
//            result.getAllErrors().stream().filter(e -> e.getArguments().equals("matchingPassword")).findAny().ifPresent(e -> result.rejectValue("email","PasswordMatches.user"));
            return new ModelAndView("registration", "user", userDto);
        }
        registered = createUserAccount(userDto);
        if (registered == null) {
            result.rejectValue("email", "message.regError");
            return new ModelAndView("registration", "user", userDto);
        }
        return new ModelAndView("registrationSuccess", "user", userDto);
    }

    private UserEntity createUserAccount(UserDto accountDto) {
        return service.registerNewUserAccount(accountDto);
    }
}
