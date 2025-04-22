package org.demo.book.api.service;

import org.demo.book.api.dto.OrderDto;

import java.util.List;

public interface OrderCreationService {

    public OrderDto createFor(Long clientId, List<OrderDto.BookPurchaseDto> books);
}
