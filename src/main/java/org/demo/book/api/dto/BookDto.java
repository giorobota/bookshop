package org.demo.book.api.dto;

import org.demo.book.persistance.BookType;

import java.math.BigDecimal;

public record BookDto(
        Long id,
        String title,
        String author,
        BigDecimal price,
        BookType bookType
) {}
