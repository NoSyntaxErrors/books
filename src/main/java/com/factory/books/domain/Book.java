package com.factory.books.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private String name;
    private String author;
    private Integer pages;
    private String isbn;
    private String language;
}
