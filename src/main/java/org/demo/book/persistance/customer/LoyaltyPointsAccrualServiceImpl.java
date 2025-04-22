package org.demo.book.persistance.customer;

import org.demo.book.api.dto.OrderDto;
import org.demo.book.api.service.LoyaltyPointsAccrualService;
import org.springframework.stereotype.Service;

@Service
class LoyaltyPointsAccrualServiceImpl implements LoyaltyPointsAccrualService {

    private final CustomerRepository customerRepository;

    public LoyaltyPointsAccrualServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void accrueLoyaltyPointsForOrder(OrderDto order) {
        Customer customer = customerRepository.getReferenceById(order.clientId());

        int totalBooks = order.purchasedBooks().stream().mapToInt(OrderDto.BookPurchaseDto::amount).sum();

        customer.setLoyaltyPoints(customer.getLoyaltyPoints() + totalBooks);
        customerRepository.save(customer);
    }
}
