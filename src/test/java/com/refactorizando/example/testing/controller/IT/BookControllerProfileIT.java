package com.refactorizando.example.testing.controller.IT;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.refactorizando.example.testing.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.MimeTypeUtils;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class BookControllerProfileIT {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Value("${title}")
  String title;

  @Test
  void findById() throws Exception {
    var book = createBook();

    mockMvc.perform(
            MockMvcRequestBuilders.post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
        .andExpect(status().isCreated());

    var findById = mockMvc.perform(
            get("/api/books/1").accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk())
        .andReturn();

    var b = objectMapper.readValue(findById.getResponse().getContentAsString(), Book.class);

    assert b.getIsbn().equalsIgnoreCase("1A2s-3f");
    assert b.getTitle().equalsIgnoreCase("Harry Potter");

  }

  private Book createBook() {

    Book book = new Book();
    book.setIsbn("1A2s-3f");
    book.setTitle(title);
    book.setPrice(34);

    return book;

  }
}