package com.refactorizando.example.testing.controller.UnitTest;


import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.refactorizando.example.testing.controller.BookController;
import com.refactorizando.example.testing.entity.Book;
import com.refactorizando.example.testing.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.MimeTypeUtils;


@WebMvcTest(BookController.class)
public class BookControlerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private BookService service;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void givenBooks_whenGetBookById_thenReturnBook()
      throws Exception {

    Book book = createBook();

    given(service.findById(1L)).willReturn(book);

    var findById = mockMvc.perform(
            get("/api/books/1")
                .accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andReturn();

    var b = objectMapper.readValue(findById.getResponse().getContentAsString(), Book.class);

    assert b.getIsbn().equalsIgnoreCase("1A2s-3f");
  }

  private Book createBook() {

    Book book = new Book();
    book.setIsbn("1A2s-3f");
    book.setTitle("Numancia");
    book.setPrice(34);

    return book;

  }

}
