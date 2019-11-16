package com.lambdaschool.usermodel.services.bookservice;


import com.lambdaschool.usermodel.models.Author;
import com.lambdaschool.usermodel.models.Book;

import java.util.List;

public interface IBookAndAuthorService {
    List<Book> findAllBooks();
    Book updateBook(long bookId, String newTitle, Integer newYearPublished, String newIsbn);
    Author addAuthorToBook(long bookID, long authorId);
    void deleteBook(long bookId);
    List<Author> findAllAuthors();
}
