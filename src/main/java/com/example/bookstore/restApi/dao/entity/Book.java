package com.example.bookstore.restApi.dao.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Getter @Setter @NoArgsConstructor
@XmlRootElement(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String id;

    @NotNull
    public String title;

    @NotNull
    public String author;

    public Book(String title, String author) {

        this.title = title;
        this.author = author;
    }
}
