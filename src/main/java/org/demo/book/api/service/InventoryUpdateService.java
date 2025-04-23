package org.demo.book.api.service;

public interface InventoryUpdateService {

    void deductInventory(Long bookId, int quantity);
}
