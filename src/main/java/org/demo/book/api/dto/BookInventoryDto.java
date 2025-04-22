package org.demo.book.api.dto;

public record BookInventoryDto(
        Long bookId,
        Integer amount
) {}