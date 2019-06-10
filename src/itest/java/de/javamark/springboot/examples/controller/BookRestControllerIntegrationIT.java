package de.javamark.springboot.examples.controller;

import de.javamark.springboot.examples.DemoApplication;
import de.javamark.springboot.examples.IntegrationTest;
import de.javamark.springboot.examples.domain.Book;
import de.javamark.springboot.examples.repositories.BookRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = DemoApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@Category(IntegrationTest.class)
public class BookRestControllerIntegrationIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void givenBooks_whenGetBooks_statusIsOk() throws Exception {

        Book coraline = new Book("Coraline", "Neil Gaiman");
        Book goodOmens = new Book("Good Omens", "Neil Gaiman");
        addBook(coraline);
        addBook(goodOmens);

        mockMvc.perform(get("/api/books").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", equalTo("Coraline")));
    }

    @After
    public void tearDown() throws Exception {
        bookRepository.deleteAll();
    }

    private void addBook(Book book) {
        this.bookRepository.saveAndFlush(book);
    }
}
