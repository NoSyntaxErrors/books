package com.factory.books.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private String nombre;
    private Integer paginas;
    private String isbn;
    private String autor;
    private String idioma;

}
