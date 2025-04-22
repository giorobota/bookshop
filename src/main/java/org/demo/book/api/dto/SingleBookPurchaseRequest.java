package org.demo.book.api.dto;

public record SingleBookPurchaseRequest(
        Long bookId,
        Integer amount
) {
}
