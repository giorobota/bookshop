package org.demo.book.api.service;

import org.demo.book.api.dto.BookInventoryDto;

public interface BookInventoryQueryService {

    public BookInventoryDto getBookInventory(Long bookId);
}
