package de.javamark.springboot.examples.repositories;

import de.javamark.springboot.examples.domain.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryIntegrationTest {
    @Autowired
    public TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void whenFindByName_thenReturnBook() {

        Book book = new Book("Stardust", "Neil Gaiman");
        entityManager.persist(book);
        entityManager.flush();

        // when
        Book fromDatabase = bookRepository.findByName(book.getName());

        // then
        assertThat(fromDatabase.getName()).isEqualTo(book.getName());
        assertThat(fromDatabase.getAuthor()).isEqualTo(book.getAuthor());
    }
}
