package org.demo.book.persistance.book;

import org.demo.book.api.service.InventoryUpdateService;
import org.springframework.stereotype.Service;

@Service
class InventoryUpdateServiceImpl implements InventoryUpdateService {

    private final BookInventoryRepository bookInventoryRepository;

    public InventoryUpdateServiceImpl(BookInventoryRepository _bookInventoryRepository) {
        this.bookInventoryRepository = _bookInventoryRepository;
    }

    @Override
    public void deductInventory(Long bookId, int quantity) {
        BookInventory bookInventory = bookInventoryRepository.getByBookId(bookId);
        if (bookInventory.getAmount() < quantity) {
            throw new IllegalArgumentException("Not enough inventory");
        }
        bookInventory.setAmount(bookInventory.getAmount() - quantity);
        bookInventoryRepository.saveAndFlush(bookInventory);
    }
}
