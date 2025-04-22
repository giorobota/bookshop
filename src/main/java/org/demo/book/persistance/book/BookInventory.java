package org.demo.book.persistance.book;

import jakarta.persistence.*;

@Entity
@Table(name = "book_inventory")
@SequenceGenerator(
        name = "books_inventory_seq_gen",
        sequenceName = "books_inventory_seq",
        allocationSize = 1
)
class BookInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "books_inventory_seq_gen")
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    private Book book;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
