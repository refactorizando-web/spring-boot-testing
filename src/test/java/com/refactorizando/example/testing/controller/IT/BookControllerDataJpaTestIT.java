package com.refactorizando.example.testing.controller.IT;

import static org.assertj.core.api.Assertions.assertThat;

import com.refactorizando.example.testing.entity.Book;
import com.refactorizando.example.testing.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class BookControllerDataJpaTestIT {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private BookRepository bookRepository;

  @Test
  public void whenFindByName_thenReturnBook() {
    // given
    Book book = createBook();

    entityManager.persist(book);
    entityManager.flush();

    // when
    Book b = bookRepository.findByTitle("The Count of Monte Cristo").orElseThrow();

    // then
    assertThat(b.getTitle())
        .isEqualTo(book.getTitle());
  }

  private Book createBook() {

    Book book = new Book();
    book.setIsbn("1A2s-3f");
    book.setTitle("The Count of Monte Cristo");
    book.setPrice(34);

    return book;

  }

}