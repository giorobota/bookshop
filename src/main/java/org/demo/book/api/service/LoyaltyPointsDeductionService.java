package org.demo.book.api.service;

public interface LoyaltyPointsDeductionService {

    public void payWithLoyaltyPoints(Long clientId, int points);
}
