package com.lambdaschool.usermodel.services.bookservice;

import com.lambdaschool.usermodel.exceptions.ResourceNotFoundException;
import com.lambdaschool.usermodel.models.Author;
import com.lambdaschool.usermodel.models.Book;
import com.lambdaschool.usermodel.repository.AuthorRepository;
import com.lambdaschool.usermodel.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "bookService")
public class BookAndAuthorService implements IBookAndAuthorService {

    @Autowired
    BookRepository bookRepo;

    @Autowired
    AuthorRepository authorRepo;

    @Override
    public List<Book> findAllBooks() {
       return (List<Book>) bookRepo.findAll();
    }

    @Transactional
    @Override
    public Book updateBook(long bookId, String newTitle, Integer newYearPublished, String newIsbn) {

        Book updatedBook;
        Optional<Book> optCurrentBook = bookRepo.findById(bookId);
        if(optCurrentBook.isPresent()) {
            updatedBook = optCurrentBook.get();
            if (newTitle.isBlank() == false) {
                updatedBook.setTitle(newTitle);
            }
            if (newYearPublished != null) {
                updatedBook.setYearPublished(newYearPublished);
            }
            if(newIsbn.isBlank() == false) {
                updatedBook.setIsbn(newIsbn);
            }
            return bookRepo.save(updatedBook);
        } else {
           throw new ResourceNotFoundException("Book with id" + " " + Long.toString(bookId) + " " + "does not exist");
        }

    }

    @Transactional
    @Override
    public Author addAuthorToBook(long bookID, long authorId) {
        Book book;
        Optional<Book> optBook = bookRepo.findById(bookID);
        Author authorToAdd;
        Optional<Author> optAuthorToAdd = authorRepo.findById(authorId);
        if (optAuthorToAdd.isPresent() == false) {
            throw new ResourceNotFoundException("Blah");
        } else if (optBook.isPresent() == false) {
            throw new ResourceNotFoundException("Blah");
        } else {
            authorToAdd = optAuthorToAdd.get();
            book = optBook.get();
            authorToAdd.addBook(book);
            return authorRepo.save(authorToAdd);
        }
    }

    @Override
    public void deleteBook(long bookId) {
        Book bookToDelete;
        Optional<Book> optBookToDelete = bookRepo.findById(bookId);
        if(optBookToDelete.isPresent()) {
            bookToDelete = optBookToDelete.get();
            bookRepo.delete(bookToDelete);
        } else {
            throw new ResourceNotFoundException("Book with id" + " " + Long.toString(bookId) + " " + "does not exist");
        }
    }

    @Override
    public List<Author> findAllAuthors() {
        return (List<Author>) authorRepo.findAll();
    }
}
