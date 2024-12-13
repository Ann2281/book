package com.example.books.Controllers;

import com.example.books.Models.Author;
import com.example.books.Models.Book;
import com.example.books.Models.Publish;
import com.example.books.Repository.AuthorRepository;
import com.example.books.Repository.BookRepository;
import com.example.books.Repository.PublishRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/Book")
@Controller
@SessionAttributes({"books","user","authors","publishes"})
public class BookController {
    private final BookRepository repository;
    private final AuthorRepository authorRepository;
    private final PublishRepository publishRepository;

    @Autowired
    public BookController(BookRepository repository, AuthorRepository authorRepository,PublishRepository publishRepository) {
        this.repository = repository;
        this.publishRepository = publishRepository;
        this.authorRepository = authorRepository;
    }


    @GetMapping
    public String Read(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Book newBook = new Book();
        model.addAttribute("book",newBook);
        Iterable<Book> books = repository.findAll();
        Iterable<Author> authors = authorRepository.findAll();
        Iterable<Publish> publishes = publishRepository.findAll();
        model.addAttribute("books", books);
        model.addAttribute("authors", authors);
        model.addAttribute("publishes", publishes);
        model.addAttribute("user", userDetails==null ? "Войти" : userDetails.getUsername()+": Выйти");
        return "Book";}


    @PostMapping(value = "/add")
    public String Add(@Valid @ModelAttribute(value = "book") Book book, BindingResult result) {
        if (result.hasErrors()) return "Book";
        repository.save(book); return "redirect:/Book";
    }


    @PostMapping(value = "/delete")
    public String Delete(@ModelAttribute(value = "var") Book book) {
        repository.deleteById(book.getId()); return "redirect:/Book";}


    @PostMapping(value = "/show_update")
    public String Show_update(@ModelAttribute(value = "var") Book book, Model model) {
        model.addAttribute("book",book);
        return "BookUpdate";}


    @PostMapping(value = "/save_update")
    public String Save_update(@Valid @ModelAttribute(value = "book") Book book, BindingResult result) {
        if (result.hasErrors()) return "BookUpdate";
        repository.save(book);
        return "redirect:/Book";
    }
}