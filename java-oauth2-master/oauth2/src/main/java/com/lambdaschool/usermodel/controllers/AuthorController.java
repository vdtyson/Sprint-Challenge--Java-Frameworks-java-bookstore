package com.lambdaschool.usermodel.controllers;

import com.lambdaschool.usermodel.models.Author;
import com.lambdaschool.usermodel.models.Book;
import com.lambdaschool.usermodel.services.bookservice.IBookAndAuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    IBookAndAuthorService service;

    private static final Logger logger = LoggerFactory.getLogger(AuthorController.class);

    @GetMapping(produces = "application/json")
    ResponseEntity<?> getAllAuthors() {
        logger.info("books Accessed");
        List<Author> authors = service.findAllAuthors();
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }
}
