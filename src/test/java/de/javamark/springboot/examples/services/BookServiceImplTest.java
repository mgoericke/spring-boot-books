package de.javamark.springboot.examples.services;

import de.javamark.springboot.examples.domain.Book;
import de.javamark.springboot.examples.repositories.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class BookServiceImplTest {
    @TestConfiguration
    static class BookServiceImplTestConfiguration {
        @Bean
        public BookService bookService() {
            return new BookServiceImpl();
        }
    }

    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @Test
    public void whenKnownBookName_thenBookShouldBeFound() {
        Book book = new Book("Stardust", "Neil Gaiman");

        when(bookRepository.findByName(book.getName())).thenReturn(book);

        Book bookFromRepository = bookService.getBookByName(book.getName());

        assertThat(bookFromRepository.getName())
                .isEqualTo(book.getName());
        assertThat(bookFromRepository.getAuthor())
                .isEqualTo(book.getAuthor());

    }
}
