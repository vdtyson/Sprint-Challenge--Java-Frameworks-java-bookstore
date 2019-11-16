package com.lambdaschool.usermodel.controllers;

import com.lambdaschool.usermodel.models.Book;
import com.lambdaschool.usermodel.services.bookservice.IBookAndAuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/data")
public class DataController {
  @Autowired private IBookAndAuthorService service;

  private static final Logger logger = LoggerFactory.getLogger(DataController.class);

  @PutMapping(value = "/books/{id}", consumes = "application/json")
  ResponseEntity<?> updateBook(@PathVariable long id, @RequestBody Book book) {
    logger.info("books/" + id + "accessed for update.");
    service.updateBook(book.getId(), book.getTitle(), book.getYearPublished(), book.getIsbn());
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PostMapping(value = "/books/{bookid}/authors/{authorid}")
  ResponseEntity<?> addAuthor(@PathVariable long bookid, @PathVariable long authorid) {
    service.addAuthorToBook(bookid, authorid);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @DeleteMapping(value = "/books/{id}")
  ResponseEntity<?> deleteBook(@PathVariable long id) {
    service.deleteBook(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
