package com.refactorizando.example.testing.controller.UnitTest;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertTrue;

import com.refactorizando.example.testing.entity.Book;
import com.refactorizando.example.testing.repository.BookRepository;
import com.refactorizando.example.testing.service.BookService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookServiceTest {

  @Autowired
  private BookService bookService;

  @MockBean
  private BookRepository bookRepository;

  @Test
  public void testRetrieveBookWithMockRepository() throws Exception {

    Optional<Book> optStudent = Optional.of(createBook());

    when(bookRepository.findById(1L)).thenReturn(optStudent);

    assert bookService.findById(1L).getTitle().contains("Numancia");

  }

  private Book createBook() {

    Book book = new Book();
    book.setIsbn("1A2s-3f");
    book.setTitle("Numancia");
    book.setPrice(34);

    return book;

  }
}