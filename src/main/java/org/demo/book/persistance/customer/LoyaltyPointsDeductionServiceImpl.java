package org.demo.book.persistance.customer;

import org.demo.book.api.service.LoyaltyPointsDeductionService;
import org.springframework.stereotype.Service;

@Service
class LoyaltyPointsDeductionServiceImpl implements LoyaltyPointsDeductionService {

    private final CustomerRepository customerRepository;

    public LoyaltyPointsDeductionServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void payWithLoyaltyPoints(Long clientId, int points) {
        Customer customer = customerRepository.getReferenceById(clientId);

        if(customer.getLoyaltyPoints() < points) {
            throw new IllegalArgumentException("You do not have enough points for payment");
        }

        customer.setLoyaltyPoints(customer.getLoyaltyPoints() - points);
        customerRepository.save(customer);
    }
}
