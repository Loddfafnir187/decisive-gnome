package com.example.clients.controllers;

import com.example.clients.Services.UserService;
import com.example.clients.models.WishList;
import com.example.storage.models.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private UserService userService;
    private Long userId;

    public UserController(UserService userService) {
        this.userService = userService;
        userId=1L;
    }


    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("client", userService.getClient(userId));
        model.addAttribute("products", userService.getAllProductFromExternalApi());
        return "index";
    }

    @GetMapping("/product/{id}")
    public String productById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product",  userService.getProdById(id));
        return "product";
    }

    @PostMapping("/buy/{id}")
    public String buyProd(@PathVariable("id") Long id, @RequestParam int amount) {
        userService.buyProduct(id, amount, userId);
        return "redirect:/cart";
    }

    @PostMapping("/pick/{id}")
    public String pick(@PathVariable("id") Long id) {
        userService.addToWishList(id, userId);
        return "redirect:/";
    }

    @GetMapping("/cart")
    public String getCart(Model model) {
        List<Product> products = new ArrayList<>();
        for (WishList wishList :
             userService.getClient(userId).getWishlist()) {
            products.add(userService.getProdById(wishList.getProductId()));
        }
        model.addAttribute("products", products);
        return "cart";
    }

    @GetMapping("/admin/orders")
    public String getOrdersList(Model model) {
        model.addAttribute("orders", userService.getAllOrders());
        return "orders";
    }



    @PostMapping("/ch/{id}")
    public String changeUser(@PathVariable("id") Long id) {
        userId = id;
        return "redirect:/";
    }
}
