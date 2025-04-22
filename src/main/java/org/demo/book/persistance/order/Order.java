package org.demo.book.persistance.order;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.util.List;

@Entity
@Table(name = "orders")
@SequenceGenerator(
        name = "orders_seq_gen",
        sequenceName = "orders_seq",
        allocationSize = 1
)
class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_seq_gen")
    private Long id;

    @Column(name = "client_id")
    private Long clientId;

    @Type(JsonType.class)
    @Column(name = "purchased_books", columnDefinition = "jsonb")
    private List<BookPurchase> purchasedBooks;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<BookPurchase> getPurchasedBooks() {
        return purchasedBooks;
    }

    public void setPurchasedBooks(List<BookPurchase> purchasedBooks) {
        this.purchasedBooks = purchasedBooks;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
