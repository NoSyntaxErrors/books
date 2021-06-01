package com.factory.books.controller;

import java.util.List;

import com.factory.books.domain.Book;
import com.factory.books.dto.BookDto;
import com.factory.books.service.BooksService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/books")
public class BooksController {

    @Autowired
    private BooksService booksService;

    @GetMapping(path = "/")
    public List<Book> getAll() {
        return booksService.getAllBooks();
    }

    @PostMapping(path = "/create")
    public void create(@RequestBody BookDto bookDto) {
        booksService.createBook(bookDto);
    }

    @GetMapping(path = "/get/{value}")
    public Book get(@PathVariable("value") String value) {
        return booksService.getBook(value);
    }

    @PostMapping(path = "/update/{isbn}")
    public void update(@RequestBody BookDto bookDto, @PathVariable("isbn") String isbn) {
        booksService.updateBook(bookDto, isbn);
    }

    @DeleteMapping(path = "/delete/{isbn}")
    public void delete(@PathVariable("isbn") String isbn) {
        booksService.deleteBook(isbn);
    }

}
