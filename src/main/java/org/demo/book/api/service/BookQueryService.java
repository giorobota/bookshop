package org.demo.book.api.service;

import org.demo.book.api.dto.BookDto;

import java.util.List;

public interface BookQueryService {
    public BookDto getById(Long id);
    public List<BookDto> listBooks();
}
