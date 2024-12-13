package com.example.books.Controllers;


import com.example.books.Models.Publish;
import com.example.books.Repository.PublishRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/Publish")
@Controller
@SessionAttributes({"publishes","user"})
public class PublishController {

    private final PublishRepository repository;

    @Autowired
    public PublishController(PublishRepository repository) {this.repository = repository;}


    @GetMapping
    public String Read(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Publish newPublish = new Publish();
        model.addAttribute("publish",newPublish);
        Iterable<Publish> publishes = repository.findAll();
        model.addAttribute("publishes", publishes);
        model.addAttribute("user", userDetails==null ? "Войти" : userDetails.getUsername()+": Выйти");
        return "Publish";}


    @PostMapping(value = "/add")
    public String Add(@Valid @ModelAttribute(value = "publish") Publish publish, BindingResult result) {
        if (result.hasErrors()) return "Publish";
        repository.save(publish); return "redirect:/Publish";
    }


    @PostMapping(value = "/delete")
    public String Delete(@ModelAttribute(value = "var") Publish publish) {
        repository.deleteById(publish.getId()); return "redirect:/Publish";}


    @PostMapping(value = "/show_update")
    public String Show_update(@ModelAttribute(value = "var") Publish publish, Model model) {
        model.addAttribute("publish",publish);
        return "PublishUpdate";}


    @PostMapping(value = "/save_update")
    public String Save_update(@Valid @ModelAttribute(value = "publish") Publish publish, BindingResult result) {
        if (result.hasErrors()) return "PublishUpdate";
        repository.save(publish);
        return "redirect:/Publish";
    }
}
