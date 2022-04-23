package com.example.lab2_emt.web.controller;

import com.example.lab2_emt.model.Book;
import com.example.lab2_emt.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping
    public String getBookPage(Model model){
        List<Book> bookList=this.bookService.findAll();
        model.addAttribute("books",bookList);
        model.addAttribute("bodyContent","books");
        return "master-template";
    }
    @GetMapping("/add-form")
    public String getAddForm(Model model){
        model.addAttribute("bodyContent","add-product");
        return "master-template";
    }
}
