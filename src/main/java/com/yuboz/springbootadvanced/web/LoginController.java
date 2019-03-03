package com.yuboz.springbootadvanced.web;

import com.yuboz.springbootadvanced.domain.User;
import com.yuboz.springbootadvanced.domain.UserRepository;
import com.yuboz.springbootadvanced.form.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("userForm",new UserForm());
        return "register";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @PostMapping("/register")
    public String registerPost(@Valid UserForm userForm, BindingResult result, Model model){
        boolean boo = true;

        if (!userForm.getPassword().equals(userForm.getConfirmpassword())){

            result.rejectValue("confirmpassword","confirmError","Two passwords are different");
            boo = false;
        }

        if(result.hasErrors()){
            List<FieldError> fieldErrors = result.getFieldErrors();

            for(FieldError error : fieldErrors){
                System.out.println(error.getField() + " : "+error.getDefaultMessage()+" : "+ error.getCode());
            }
            boo = false;

        }



        if(!boo){
            return "register";
        }
        User user = userForm.convertToUser();
        userRepository.save(user);
        return "redirect:/login";

    }

}
