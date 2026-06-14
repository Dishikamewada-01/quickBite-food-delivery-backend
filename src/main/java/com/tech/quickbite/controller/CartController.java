package com.tech.quickbite.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tech.quickbite.dto.request.AddToCartRequest;
import com.tech.quickbite.dto.response.CartResponse;
import com.tech.quickbite.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private CartService cartService;

    public CartController(
            CartService cartService) {

        this.cartService = cartService;
    }

    @PostMapping("/add")
    public ResponseEntity<CartResponse>
    addToCart(
            @RequestBody AddToCartRequest request) {

        return ResponseEntity.ok(
                cartService.addToCart(request));
    }

    @GetMapping
    public ResponseEntity<CartResponse>
    viewCart() {

        return ResponseEntity.ok(
                cartService.viewCart());
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity<String>
    removeItem(
            @PathVariable Long id) {

        cartService.removeItem(id);

        return ResponseEntity.ok(
                "Item removed successfully");
    }

    @DeleteMapping("/clear")
    public ResponseEntity<String>
    clearCart() {

        cartService.clearCart();

        return ResponseEntity.ok(
                "Cart cleared successfully");
    }
}