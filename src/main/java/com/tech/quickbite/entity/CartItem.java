package com.tech.quickbite.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    private Double subtotal;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "food_item_id")
    private FoodItem foodItem;

    public CartItem() {
    }

    public CartItem(Long id,
                    Integer quantity,
                    Double subtotal,
                    Cart cart,
                    FoodItem foodItem) {

        this.id = id;
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.cart = cart;
        this.foodItem = foodItem;
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public Cart getCart() {
        return cart;
    }

    public FoodItem getFoodItem() {
        return foodItem;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setFoodItem(FoodItem foodItem) {
        this.foodItem = foodItem;
    }
}