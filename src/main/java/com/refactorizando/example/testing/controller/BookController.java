package com.refactorizando.example.testing.controller;

import com.refactorizando.example.testing.entity.Book;
import com.refactorizando.example.testing.repository.BookRepository;
import com.refactorizando.example.testing.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RequiredArgsConstructor
@RestController
public class BookController {

  private final BookService bookService;

  @GetMapping("/books/{id}")
  public ResponseEntity<Book> getBookById(@PathVariable Long id) {

    return ResponseEntity.ok(bookService.findById(id));
  }

  @PostMapping("/books")
  public ResponseEntity<Book> saveBook(@RequestBody Book book) {

    var save = bookService.saveBook(book);

    return new ResponseEntity<>(HttpStatus.CREATED);

  }
}
