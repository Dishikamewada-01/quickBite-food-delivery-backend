package com.tech.quickbite.service;

import com.tech.quickbite.dto.request.AddToCartRequest;
import com.tech.quickbite.dto.response.CartResponse;

public interface CartService {

    CartResponse addToCart(
            AddToCartRequest request);

    CartResponse viewCart();

    void removeItem(
            Long cartItemId);

    void clearCart();
}