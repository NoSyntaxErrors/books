package com.factory.books.service;

import java.util.List;

import com.factory.books.domain.Book;
import com.factory.books.dto.BookDto;

public interface BooksService {

    List<Book> getAllBooks();

    void createBook(BookDto books);

    Book getBook(String value);

    void updateBook(BookDto book, String isbn);

    void deleteBook(String isbn);

}
