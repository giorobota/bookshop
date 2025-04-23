package org.demo.book.persistance.book;

import org.demo.book.api.dto.OrderDto;
import org.demo.book.api.dto.SingleBookPurchaseRequest;
import org.demo.book.api.service.BookCashPurchaseService;
import org.demo.book.api.service.InventoryUpdateService;
import org.demo.book.api.service.LoyaltyPointsAccrualService;
import org.demo.book.api.service.OrderCreationService;
import org.demo.book.persistance.PaymentType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
class BookCashPurchaseServiceImpl implements BookCashPurchaseService {

    private static final int BOOK_SALE_THRESHOLD_AMOUNT = 3;

    private final BookRepository bookRepository;
    private final OrderCreationService orderCreationService;
    private final LoyaltyPointsAccrualService loyaltyPointsAccrualService;
    private final InventoryUpdateService inventoryUpdateService;

    public BookCashPurchaseServiceImpl(
            BookRepository bookRepository,
            OrderCreationService orderCreationService,
            LoyaltyPointsAccrualService loyaltyPointsAccrualService,
            InventoryUpdateService inventoryUpdateService
    ) {
        this.bookRepository = bookRepository;
        this.orderCreationService = orderCreationService;
        this.loyaltyPointsAccrualService = loyaltyPointsAccrualService;
        this.inventoryUpdateService = inventoryUpdateService;
    }

    @Override
    @Transactional
    public OrderDto purchaseBooksWithCash(Long clientId, List<SingleBookPurchaseRequest> books) {
        books.forEach(purchaseRequest ->
                inventoryUpdateService.deductInventory(purchaseRequest.bookId(), purchaseRequest.amount())
        );

        List<OrderDto.BookPurchaseDto> bookPurchaseList = toBookPurchase(books);

        OrderDto order = orderCreationService.createFor(clientId, bookPurchaseList);

        loyaltyPointsAccrualService.accrueLoyaltyPointsForOrder(order);

        return order;
    }

    private List<OrderDto.BookPurchaseDto> toBookPurchase(List<SingleBookPurchaseRequest> books) {
        return books.stream().map(purchaseRequest -> {
            Book book = bookRepository.getReferenceById(purchaseRequest.bookId());

            BigDecimal discount = book.getBookType().getBaseDiscount();

            // Apply bundle discount if 3 or more books of any kind are purchased
            if (books.size() >= BOOK_SALE_THRESHOLD_AMOUNT) {
                discount = discount.add(book.getBookType().getBundleDiscount());
            }

            BigDecimal discountedPrice = book.getPrice().multiply(BigDecimal.ONE.subtract(discount)).setScale(2, RoundingMode.HALF_UP);

            BigDecimal totalPrice = discountedPrice.multiply(BigDecimal.valueOf(purchaseRequest.amount()));
            return new OrderDto.BookPurchaseDto(book.getId(), purchaseRequest.amount(), totalPrice, PaymentType.CASH);
        }).toList();
    }
}
