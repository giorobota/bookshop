package org.demo.book.api.service;

import org.demo.book.api.dto.OrderDto;

public interface LoyaltyPointsAccrualService {
    public void accrueLoyaltyPointsForOrder(OrderDto order);
}
