package com.example.cart.controllers;

import com.example.cart.models.Cart;
import com.example.cart.models.Position;
import com.example.cart.services.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;

    @GetMapping
    public ResponseEntity<List<Cart>> getAllShoppingCarts() {
        List<Cart> shoppingCarts = cartService.getAllShoppingCarts();
        return ResponseEntity.ok().body(cartService.getAllShoppingCarts());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Cart> getShoppingCartById(@PathVariable Long id) {
        Optional<Cart> shoppingCart = Optional.ofNullable(cartService.getShoppingCartById(id));
        return shoppingCart.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cart> createShoppingCart(@RequestBody Cart shoppingCart) {
        Cart createdShoppingCart = cartService.createShoppingCart(shoppingCart);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdShoppingCart);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Cart> addShoppingCartById(@PathVariable Long id,
                                                       @RequestParam Long clientId,
                                                       @RequestParam int quantity) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(cartService.addtoShoppingCart(id, clientId, quantity));
    }
}
