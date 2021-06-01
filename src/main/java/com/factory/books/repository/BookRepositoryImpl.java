package com.factory.books.repository;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import com.factory.books.domain.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookRepositoryImpl implements BookRepository {

    @Autowired
    private Firestore db;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public List<Book> getAllBooks() {

        try {
            return db.collection("books").get().get().getDocuments().stream().map(book -> convertInBook(book.getData()))
                    .collect(Collectors.toList());
        } catch (InterruptedException | ExecutionException e) {
            log.error("Hubo un error", e);
            return null;
        }

    }

    @Override
    public void createBook(Book book) {

        try {

            if (db.collection("books").whereEqualTo("isbn", book.getIsbn()).get().get().getDocuments().size() == 0) {
                db.collection("books").add(book);
            } else {
                updateBook(book, book.getIsbn());
            }

        } catch (InterruptedException | ExecutionException e) {
            log.error("Hubo un error", e);
        }

    }

    @Override
    public Book getBook(String value) {

        try {

            return convertInBook(db.collection("books").get().get().getDocuments().stream().findFirst()
                    .filter(book -> book.getString("name").equalsIgnoreCase(value)
                            || book.getString("isbn").equalsIgnoreCase(value))
                    .get().getData());

        } catch (InterruptedException | ExecutionException e) {
            log.error("Hubo un error", e);
            return null;
        }

    }

    @Override
    public void updateBook(Book book, String isbn) {

        try {

            QueryDocumentSnapshot document = db.collection("books").whereEqualTo("isbn", isbn).get().get()
                    .getDocuments().get(0);

            db.collection("books").document(document.getId()).set(book);

        } catch (InterruptedException | ExecutionException e) {
            log.error("Hubo un error", e);
        }
    }

    @Override
    public void deleteBook(String isbn) {
        try {
            db.collection("books").whereEqualTo("isbn", isbn).get().get().getDocuments().get(0).getReference().delete();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Hubo un error", e);
        }
    }

    private Book convertInBook(Object data) {

        try {
            return mapper.convertValue(data, Book.class);
        } catch (IllegalArgumentException e) {
            throw e;
        }

    }

}
