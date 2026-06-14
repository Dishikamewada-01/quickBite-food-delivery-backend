package com.tech.quickbite.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double totalAmount;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Cart() {
    }

    public Cart(Long id,
                Double totalAmount,
                User user) {

        this.id = id;
        this.totalAmount = totalAmount;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public User getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setUser(User user) {
        this.user = user;
    }
}