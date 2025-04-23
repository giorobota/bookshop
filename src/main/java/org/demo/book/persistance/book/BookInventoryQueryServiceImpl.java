package org.demo.book.persistance.book;

import org.demo.book.api.dto.BookInventoryDto;
import org.demo.book.api.service.BookInventoryQueryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class BookInventoryQueryServiceImpl implements BookInventoryQueryService {

    private final BookInventoryRepository bookInventoryRepository;

    public BookInventoryQueryServiceImpl(BookInventoryRepository bookInventoryRepository) {
        this.bookInventoryRepository = bookInventoryRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public BookInventoryDto getBookInventory(Long bookId) {
        return toDto(bookInventoryRepository.getByBookId(bookId));
    }

    private BookInventoryDto toDto(BookInventory bookInventory) {
        return new BookInventoryDto(
                bookInventory.getBook().getId(),
                bookInventory.getAmount()
        );
    }
}
