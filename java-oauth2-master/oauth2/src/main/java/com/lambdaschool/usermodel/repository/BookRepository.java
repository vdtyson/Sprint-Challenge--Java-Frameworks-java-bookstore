package com.lambdaschool.usermodel.repository;

import com.lambdaschool.usermodel.models.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
}
