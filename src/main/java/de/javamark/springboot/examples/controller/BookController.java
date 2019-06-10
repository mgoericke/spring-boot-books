package de.javamark.springboot.examples.controller;

import de.javamark.springboot.examples.domain.Book;
import de.javamark.springboot.examples.exceptions.BookNotFoundException;
import de.javamark.springboot.examples.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return this.bookService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable Long id) {
        return this.bookService.getBookById(id);
    }
    @PostMapping("/books")
    public Book addBook(@RequestBody @NotNull Book book) {
        return this.bookService.addBook(book);
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity handleBookNotFoundException(BookNotFoundException ex) {
        return ResponseEntity.notFound().build();

    }
}
