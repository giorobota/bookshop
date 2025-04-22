package org.demo.book.persistance.book;

import org.demo.book.api.service.BookQueryService;
import org.demo.book.api.dto.BookDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookQueryServiceImpl implements BookQueryService {

    private final BookRepository bookRepository;

    public BookQueryServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDto getById(Long id) {
        return dtoFrom(bookRepository.getReferenceById(id));
    }

    @Override
    public List<BookDto> listBooks() {
        return bookRepository.findAll().stream().map(this::dtoFrom).toList();
    }

    private BookDto dtoFrom(Book book) {
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPrice(),
                book.getBookType()
        );
    }
}
