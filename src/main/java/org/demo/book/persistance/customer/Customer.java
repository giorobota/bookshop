package org.demo.book.persistance.customer;

import jakarta.persistence.*;

@Entity
@Table(name = "customers")
@SequenceGenerator(
        name = "customers_seq_gen",
        sequenceName = "customers_seq",
        allocationSize = 1
)
class Customer {

    @Id
    @GeneratedValue(generator = "customers_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "loyalty_points")
    private int loyaltyPoints;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }
}
