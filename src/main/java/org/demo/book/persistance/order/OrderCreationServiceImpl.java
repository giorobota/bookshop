package org.demo.book.persistance.order;

import org.demo.book.api.dto.OrderDto;
import org.demo.book.api.service.OrderCreationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderCreationServiceImpl implements OrderCreationService {

    private OrderRepository orderRepository;

    public OrderCreationServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderDto createFor(Long clientId, List<OrderDto.BookPurchaseDto> books) {
        Order order = new Order();
        order.setClientId(clientId);
        order.setPurchasedBooks(
                books.stream().map(bookPurchase ->
                        new BookPurchase(
                                bookPurchase.bookId(),
                                bookPurchase.amount(),
                                bookPurchase.price(),
                                bookPurchase.paymentType()
                        )
                ).toList()
        );

        order = orderRepository.save(order);

        return toDto(order);
    }

    private OrderDto toDto(Order order) {
        return new OrderDto(
                order.getId(),
                order.getClientId(),
                order.getPurchasedBooks().stream().map(bookPurchase ->
                        new OrderDto.BookPurchaseDto(
                                bookPurchase.bookId(),
                                bookPurchase.amount(),
                                bookPurchase.price(),
                                bookPurchase.paymentType()
                        )
                ).toList()
        );
    }
}
