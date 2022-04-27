package com.example.bookstore.webClient.controller;

import com.example.bookstore.webClient.manager.BookstoreService;
import com.example.bookstore.webClient.model.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class BookstoreWebClient {

    private final BookstoreService bookstoreService;
    private Model modelObject;

    public BookstoreWebClient(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    @GetMapping(value="/booklist" )
    String getBookList(Model model){
        List<Book> books = new ArrayList<>();
        String message = null;
        Boolean correct = true;

        if(checkValueGlobalModelObject()){
            message = (String) this.modelObject.getAttribute("message");
            correct = (Boolean) this.modelObject.getAttribute("correct");
        }
        this.modelObject = null;

        try{
            books = bookstoreService.getBooksList();
        }catch (HttpClientErrorException e){
            correct = false;
            message = e.getMessage();
        }catch (JsonProcessingException e){
            correct = false;
            message = e.getMessage();
        }

        model.addAttribute("books",books);
        model.addAttribute("message",message);
        model.addAttribute("correct",correct);
        return "bookListPage";
    }

    @GetMapping("/add")
    String addBookForm(Model model){
        Book book = new Book();
        model.addAttribute("book",book);
        return "addBook";
    }


    @GetMapping("/get")
    public String getBookData(@RequestParam("id")String id, Model model){
        Book book = null;
        String message = null;
        Boolean correct = true;

        if(checkValueGlobalModelObject()){
            message = (String) this.modelObject.getAttribute("message");
            correct = (Boolean) this.modelObject.getAttribute("correct");
        }
        this.modelObject = null;

        try{
            book = bookstoreService.getBook(id);
        }catch (HttpClientErrorException e){
            correct = false;
            message = e.getMessage();
        }catch (JsonProcessingException e){
            correct = false;
            message = e.getMessage();
        }

        model.addAttribute("book",book);
        model.addAttribute("correct",correct);
        model.addAttribute("error",message);
        return "addBook";
    }

    @PostMapping("/booklist")
    void saveBook(Book book, Model model, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String message;
        Boolean correct = true;

        try{
            message = bookstoreService.saveBook(book);
            if(message!=null) {
                correct = false;
            }
        }catch (HttpClientErrorException e){
            message = e.getMessage();
            correct = false;
        }

        model.addAttribute("correct",correct);
        model.addAttribute("message",message);
        this.modelObject = model;
        resp.sendRedirect(req.getContextPath() + "/booklist");
    }

    @PostMapping("/booklist/delete")
    void deleteBook(@RequestParam("id")String id, Model model, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String message = null;
        Boolean correct = true;

        try{
            bookstoreService.deleteBook(id);
        }catch (HttpClientErrorException e){
            message = e.getMessage();
            correct = false;
        }

        model.addAttribute("correct",correct);
        model.addAttribute("message",message);

        this.modelObject = model;
        resp.sendRedirect(req.getContextPath() + "/booklist");
    }

    private Boolean checkValueGlobalModelObject(){
        Boolean result = false;
        if(this.modelObject!=null) {
            if (!this.modelObject.asMap().isEmpty() && Objects.equals(this.modelObject.getAttribute("correct"), false)) {
                result = true;
            }
        }
        return result;
    }
}
