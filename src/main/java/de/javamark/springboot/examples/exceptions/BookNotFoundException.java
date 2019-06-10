package de.javamark.springboot.examples.exceptions;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(Long id) {
        super(String.format("Book with id %d could not be found", id));
    }
}
