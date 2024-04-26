package com.example.cart.services;

import com.example.cart.models.Cart;
import com.example.cart.models.Position;
import com.example.cart.repositories.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class CartService {

    private CartRepository cartRepository;


   public List<Cart> getAllShoppingCarts() {
        return cartRepository.findAll();
   }

   public List<Position> getAllPositionsFromCart(Cart cart){
       return cart.getItems();
   }

   public Position getPositionFromCart(Long productId, Cart cart){
       List<Position> positions = getAllPositionsFromCart(cart);
       for (Position position : positions) {
           if(position.getProductId().equals(productId)){
               return position;
           }
       }
       return null;
   }


    public Cart getShoppingCartById(Long id) {
        return cartRepository.findById(id).orElseThrow();
    }

    public Cart createShoppingCart(Cart Cart) {
        return cartRepository.save(Cart);
    }

    public Cart addtoShoppingCart(Long id, Long clientId, int quantity) {
       Cart updatedCart = getShoppingCartById(clientId);
       Position position = null;
       position.setProductId(id);
       position.setQuantity(quantity);
       updatedCart.getItems().add(position);
       return cartRepository.save(updatedCart);
    }




}
