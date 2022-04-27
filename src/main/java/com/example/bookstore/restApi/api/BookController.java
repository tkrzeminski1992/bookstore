package com.example.bookstore.restApi.api;

import com.example.bookstore.restApi.dao.entity.Book;
import com.example.bookstore.restApi.manager.BookManager;
import com.example.bookstore.restApi.manager.errorHandler.InvalidInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

@RestController
@RequestMapping("/bookstore-web")
public class BookController {

    private final BookManager bookManager;

    public BookController(BookManager bookManager) {

        this.bookManager = bookManager;
    }

    @GetMapping(value = "/book", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.status(200).body(bookManager.findAll());
    }

    @PostMapping(value="/book", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE}, consumes = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book, Errors errors) throws InvalidInputException{
        if(errors.hasErrors()){
            throw new InvalidInputException("Invalid input");
        }
        return new ResponseEntity<>(bookManager.save(book), HttpStatus.CREATED);
    }

    @PutMapping(value="/book", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE}, consumes = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public ResponseEntity<Book> updateBook(@Valid @RequestBody Book book, Errors errors)throws InvalidInputException{
        if(errors.hasErrors()){
            throw new InvalidInputException("Invalid input");
        }
        return new ResponseEntity<>(bookManager.save(book), HttpStatus.CREATED);
    }

    @GetMapping (value="/book/{bookId}", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
    public ResponseEntity<Book> getBookById(@PathVariable("bookId") String id){
        return new ResponseEntity<>(bookManager.findById(id), HttpStatus.OK);
    }

    @DeleteMapping (value="/book/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable("bookId") String id){
        bookManager.deleteById(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}
