package de.javamark.springboot.examples.services;

import de.javamark.springboot.examples.domain.Book;

import java.util.List;

public interface BookService {
    public Book getBookByName(String name);

    public List<Book> getAllBooks();
}
