package de.javamark.springboot.examples.services;

import de.javamark.springboot.examples.domain.Book;
import de.javamark.springboot.examples.exceptions.BookNotFoundException;
import de.javamark.springboot.examples.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book getBookByName(String name) {
        return bookRepository.findByName(name);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book addBook(Book book) {
        return this.bookRepository.saveAndFlush(book);
    }

    @Override
    public Book getBookById(Long id) {
        return this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }
}
