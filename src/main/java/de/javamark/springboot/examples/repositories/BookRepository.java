package de.javamark.springboot.examples.repositories;

import de.javamark.springboot.examples.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    public Book findByName(String name);
}
