package com.example.books.Controllers;


import com.example.books.Models.Author;
import com.example.books.Repository.AuthorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/Author")
@Controller
@SessionAttributes({"authors","user"})
public class AuthorController {

    private final AuthorRepository repository;

    @Autowired
    public AuthorController(AuthorRepository repository) {this.repository = repository;}


    @GetMapping
    public String Read(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Author newAuthor = new Author();
        model.addAttribute("author",newAuthor);
        Iterable<Author> authors = repository.findAll();
        model.addAttribute("authors", authors);
        model.addAttribute("user", userDetails==null ? "Войти" : userDetails.getUsername()+": Выйти");
        return "Author";}


    @PostMapping(value = "/add")
    public String Add(@Valid @ModelAttribute(value = "chair") Author author, BindingResult result) {
        if (result.hasErrors()) return "Author";
        repository.save(author); return "redirect:/Author";
    }


    @PostMapping(value = "/delete")
    public String Delete(@ModelAttribute(value = "var") Author author) {
        repository.deleteById(author.getId()); return "redirect:/Author";}


    @PostMapping(value = "/show_update")
    public String Show_update(@ModelAttribute(value = "var") Author author, Model model) {
        model.addAttribute("author",author);
        return "AuthorUpdate";}


    @PostMapping(value = "/save_update")
    public String Save_update(@Valid @ModelAttribute(value = "author") Author author, BindingResult result) {
        if (result.hasErrors()) return "AuthorUpdate";
        repository.save(author);
        return "redirect:/Author";
    }
}
