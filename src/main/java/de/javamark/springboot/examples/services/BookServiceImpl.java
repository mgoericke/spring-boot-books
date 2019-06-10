package de.javamark.springboot.examples.services;

import de.javamark.springboot.examples.domain.Book;
import de.javamark.springboot.examples.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book getBookByName(String name) {
        return bookRepository.findByName(name);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
