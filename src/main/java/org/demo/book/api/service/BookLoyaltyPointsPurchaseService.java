package org.demo.book.api.service;

import org.demo.book.api.dto.OrderDto;
import org.demo.book.api.dto.SingleBookPurchaseRequest;

import java.util.List;

public interface BookLoyaltyPointsPurchaseService {

    public OrderDto purchaseBooksWithLoyaltyPoints(Long clientId, List<SingleBookPurchaseRequest> books);
}
