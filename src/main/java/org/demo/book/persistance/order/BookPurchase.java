package org.demo.book.persistance.order;
import org.demo.book.persistance.PaymentType;

import java.math.BigDecimal;

public record BookPurchase(
        Long bookId,
        Integer amount,
        BigDecimal price,
        PaymentType paymentType
) {
}
