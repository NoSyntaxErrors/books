package com.factory.books.repository;

import java.util.List;

import com.factory.books.domain.Book;

public interface BookRepository {

    List<Book> getAllBooks();

    void createBook(Book book);

    Book getBook(String value);

    void updateBook(Book book, String isbn);

    void deleteBook(String isbn);
}
