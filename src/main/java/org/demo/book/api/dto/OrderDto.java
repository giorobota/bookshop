package org.demo.book.api.dto;

import org.demo.book.persistance.PaymentType;

import java.math.BigDecimal;
import java.util.List;

public record OrderDto(
        Long id,
        Long clientId,
        List<BookPurchaseDto> purchasedBooks
) {
    public record BookPurchaseDto(
            Long bookId,
            Integer amount,
            BigDecimal price,
            PaymentType paymentType
    ) {}
}
