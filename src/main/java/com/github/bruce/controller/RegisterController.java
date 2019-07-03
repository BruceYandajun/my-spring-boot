//package com.github.bruce.controller;
//
//import com.github.bruce.dao.entity.UserEntity;
//import com.github.bruce.model.dto.UserDto;
//import com.github.bruce.service.RegisterService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.Errors;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.validation.Valid;
//
//@RestController
//public class RegisterController {
//
//    @Autowired
//    private RegisterService service;
//
//    @GetMapping(value = "/user/registration")
//    public String showRegistrationForm(WebRequest request, Model model) {
//        UserDto userDto = new UserDto();
//        model.addAttribute("user", userDto);
//        return "registration";
//    }
//
//    @PostMapping("/user/registration")
//    public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid UserDto accountDto,
//                                            BindingResult result, WebRequest request, Errors errors) {
//
//        UserEntity registered = new UserEntity();
//        if (!result.hasErrors()) {
//            registered = createUserAccount(accountDto, result);
//        }
//        if (registered == null) {
//            result.rejectValue("email", "message.regError");
//        }
//        if (result.hasErrors()) {
//            return new ModelAndView("registration", "user", accountDto);
//        } else {
//            return new ModelAndView("successRegister", "user", accountDto);
//        }
//    }
//    private UserEntity createUserAccount(UserDto accountDto, BindingResult result) {
//        return service.registerNewUserAccount(accountDto);
//    }
//}
