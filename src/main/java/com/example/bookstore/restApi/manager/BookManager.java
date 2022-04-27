package com.example.bookstore.restApi.manager;

import com.example.bookstore.restApi.dao.BookRepository;
import com.example.bookstore.restApi.dao.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BookManager {

    private final BookRepository bookRepository;

    public BookManager(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll(){

        return bookRepository.findAll();
    }

    public Book save(Book book){

        if(book.getId() != null && !Objects.equals(book.id, "")){
            existsById(book.getId());
        }

        return bookRepository.save(book);
    }

    public Book findById(String id){

        existsById(id);
        return bookRepository.findById(id).get();
    }

    public void deleteById(String id){

        existsById(id);
        bookRepository.deleteById(id);
    }

    private void existsById(String id){

        if(!bookRepository.existsById(id)){
            throw new IllegalArgumentException("Book not found");
        }
    }
}
