package com.refactorizando.example.testing.service;

import com.refactorizando.example.testing.entity.Book;
import com.refactorizando.example.testing.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class BookService {

  private final BookRepository bookRepository;

  public Book findById(Long id) {

    log.info("Get book by id {} ", id);

    return bookRepository.findById(id).orElseThrow();

  }

  public Book findByTitle (String title) {

    log.info("Get book by title {} ", title);

    return bookRepository.findByTitle(title).orElseThrow();

  }

  public Book saveBook (Book book) {

    return bookRepository.save(book);
  }
}
