package org.demo.book.persistance.book;

import org.demo.book.api.dto.OrderDto;
import org.demo.book.api.dto.SingleBookPurchaseRequest;
import org.demo.book.api.service.BookLoyaltyPointsPurchaseService;
import org.demo.book.api.service.LoyaltyPointsDeductionService;
import org.demo.book.api.service.OrderCreationService;
import org.demo.book.persistance.BookType;
import org.demo.book.persistance.PaymentType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
class BookLoyaltyPointsPurchaseServiceImpl implements BookLoyaltyPointsPurchaseService {

    private final BookRepository bookRepository;
    private final BookInventoryRepository bookInventoryRepository;
    private final OrderCreationService orderCreationService;
    private final LoyaltyPointsDeductionService loyaltyPointsDeductionService;

    public BookLoyaltyPointsPurchaseServiceImpl(
            BookRepository bookRepository,
            BookInventoryRepository bookInventoryRepository,
            OrderCreationService orderCreationService,
            LoyaltyPointsDeductionService loyaltyPointsDeductionService
    ) {
        this.bookRepository = bookRepository;
        this.bookInventoryRepository = bookInventoryRepository;
        this.orderCreationService = orderCreationService;
        this.loyaltyPointsDeductionService = loyaltyPointsDeductionService;
    }

    @Override
    @Transactional
    public OrderDto purchaseBooksWithLoyaltyPoints(Long clientId, List<SingleBookPurchaseRequest> books) {
        books.forEach(purchaseRequest -> {
            if (bookInventoryRepository.findByBookId(purchaseRequest.bookId()).getAmount() < purchaseRequest.amount()) {
                throw new IllegalStateException("insufficient amount of books");
            }
        });

        int totalBooks = books.stream().mapToInt(SingleBookPurchaseRequest::amount).sum();

        loyaltyPointsDeductionService.payWithLoyaltyPoints(clientId, totalBooks * 10);

        List<OrderDto.BookPurchaseDto> bookPurchaseList = toBookPurchase(books);

        return orderCreationService.createFor(clientId, bookPurchaseList);
    }

    private List<OrderDto.BookPurchaseDto> toBookPurchase(List<SingleBookPurchaseRequest> books) {
        return books.stream().map(purchaseRequest -> {
            Book book = bookRepository.getReferenceById(purchaseRequest.bookId());
            if (book.getBookType() == BookType.NEW_RELEASE) {
                throw new IllegalArgumentException("NEW_RELEASE books can not be sold by points");
            }

            return new OrderDto.BookPurchaseDto(
                    book.getId(),
                    purchaseRequest.amount(),
                    BigDecimal.TEN,
                    PaymentType.LOYALTY_POINTS
            );

        }).toList();
    }
}
