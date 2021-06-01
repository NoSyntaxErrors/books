package com.factory.books.service;

import java.util.List;

import com.factory.books.domain.Book;
import com.factory.books.dto.BookDto;
import com.factory.books.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BooksServiceImpl implements BooksService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    @Override
    public void createBook(BookDto bookDto) {
        Book book = new Book();
        book.setAuthor(bookDto.getAutor());
        book.setIsbn(bookDto.getIsbn());
        book.setName(bookDto.getNombre());
        book.setLanguage(bookDto.getIdioma());
        book.setPages(bookDto.getPaginas());

        bookRepository.createBook(book);
    }

    @Override
    public Book getBook(String value) {
        return bookRepository.getBook(value);
    }

    @Override
    public void updateBook(BookDto bookDto, String isbn) {
        Book book = new Book();
        book.setIsbn(isbn);
        book.setAuthor(bookDto.getAutor());
        book.setLanguage(bookDto.getIdioma());
        book.setPages(bookDto.getPaginas());
        book.setName(bookDto.getNombre());

        bookRepository.updateBook(book, isbn);
    }

    @Override
    public void deleteBook(String isbn) {
        bookRepository.deleteBook(isbn);
    }

}
