package de.javamark.springboot.examples.services;

import de.javamark.springboot.examples.domain.Book;
import de.javamark.springboot.examples.repositories.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class BookServiceImplTest {

    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @Before
    public void setUp() throws Exception {
        this.bookService = new BookServiceImpl(this.bookRepository);
    }

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
