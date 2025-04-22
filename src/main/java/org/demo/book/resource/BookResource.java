package org.demo.book.resource;

import org.demo.book.api.dto.BookDto;
import org.demo.book.api.dto.BookInventoryDto;
import org.demo.book.api.dto.OrderDto;
import org.demo.book.api.dto.SingleBookPurchaseRequest;
import org.demo.book.api.service.BookCashPurchaseService;
import org.demo.book.api.service.BookInventoryQueryService;
import org.demo.book.api.service.BookLoyaltyPointsPurchaseService;
import org.demo.book.api.service.BookQueryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookResource {

    private final BookQueryService bookQueryService;
    private final BookInventoryQueryService bookInventoryQueryService;
    private final BookCashPurchaseService bookCashPurchaseService;
    private final BookLoyaltyPointsPurchaseService bookLoyaltyPointsPurchaseService;

    public BookResource(
            BookQueryService bookQueryService,
            BookInventoryQueryService bookInventoryQueryService,
            BookCashPurchaseService bookCashPurchaseService,
            BookLoyaltyPointsPurchaseService bookLoyaltyPointsPurchaseService
    ) {
        this.bookQueryService = bookQueryService;
        this.bookInventoryQueryService = bookInventoryQueryService;
        this.bookCashPurchaseService = bookCashPurchaseService;
        this.bookLoyaltyPointsPurchaseService = bookLoyaltyPointsPurchaseService;
    }

    @GetMapping("/books/list")
    public List<BookDto> listBooks() {
        return bookQueryService.listBooks();
    }

    @GetMapping("/books/{bookId}/inventory")
    public BookInventoryDto getBookInventory(@PathVariable("bookId") Long bookId) {
        return bookInventoryQueryService.getBookInventory(bookId);
    }

    @PostMapping("/purchase-cash")
    public OrderDto purchaseByCash(
            @RequestHeader("X-Customer-Id") Long customerId,
            @RequestBody List<SingleBookPurchaseRequest> purchaseRequest
    ) {
        return bookCashPurchaseService.purchaseBooksWithCash(customerId, purchaseRequest);
    }

    @PostMapping("/purchase-loyalty")
    public OrderDto purchaseByLoyaltyPoints(
            @RequestHeader("X-Customer-Id") Long customerId,
            @RequestBody List<SingleBookPurchaseRequest> purchaseRequest
    ) {
        return bookLoyaltyPointsPurchaseService.purchaseBooksWithLoyaltyPoints(customerId, purchaseRequest);
    }
}
