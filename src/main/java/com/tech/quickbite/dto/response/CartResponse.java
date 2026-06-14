package com.tech.quickbite.dto.response;

import java.util.List;

public class CartResponse {

    private Long cartId;

    private Double totalAmount;

    private List<CartItemResponse> items;

    public CartResponse() {
    }

    public CartResponse(
            Long cartId,
            Double totalAmount,
            List<CartItemResponse> items) {

        this.cartId = cartId;
        this.totalAmount = totalAmount;
        this.items = items;
    }

    public Long getCartId() {
        return cartId;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public List<CartItemResponse> getItems() {
        return items;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setItems(List<CartItemResponse> items) {
        this.items = items;
    }
}