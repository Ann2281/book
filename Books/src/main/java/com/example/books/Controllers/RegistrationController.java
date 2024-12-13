package com.example.books.Controllers;

import com.example.books.Forms.RegistrationForm;
import com.example.books.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Registration")
public class RegistrationController {
    private final UsersRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UsersRepository repository, PasswordEncoder passwordEncoder)
    {this.repository = repository; this.passwordEncoder = passwordEncoder;}

    @GetMapping
    public String registerForm() {
        return "Registration";
    }

    @PostMapping
    public String processRegistration(RegistrationForm registrationForm) {
        repository.save(registrationForm.toUser(passwordEncoder));
        return "redirect:/Login";
    }
}

