package com.example.clients.Services;

import com.example.cart.models.Cart;
import com.example.clients.api.Api;
import com.example.clients.models.Client;
import com.example.clients.models.CustomUser;
import com.example.clients.models.WishList;
import com.example.clients.repositories.UserRepository;
import com.example.clients.repositories.WishListRepo;
import com.example.order.models.Order;
import com.example.order.models.OrderStatus;
import com.example.storage.models.Product;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final WishListRepo wishListRepo;

    private final Api api;



    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUser) {
            CustomUser customUser = (CustomUser) authentication.getPrincipal();
            return customUser.getId();

        }
        return null;
    }


    public List<Product> getAllProductFromExternalApi(){
        RestTemplate template = new RestTemplate();
        ResponseEntity<List<Product>> response = template.exchange(api.getStorage(),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>(){});
        return response.getBody();
    }



    public Product getProdById(Long id){
        RestTemplate template = new RestTemplate();
        ResponseEntity<Product> response = template.exchange(api.getStorage() + "/" + id,
                HttpMethod.GET, null, new ParameterizedTypeReference<Product>() {});
        return response.getBody();
    }

    public void buyProduct(Long productId, int amount, Long clientId) {
        RestTemplate template = new RestTemplate();

        Order order = new Order();
        order.setClientId(clientId);
        order.setProdId(productId);
        order.setQuantity(amount);
        order.setDate(Date.from(Instant.now()));
        order.setStatus(OrderStatus.NEW);

        String path = api.getStorage() + "/" + productId + "/buy";
        template.postForEntity(path, order, Object.class);

        path = api.getOrder();
        template.postForEntity(path, order, Object.class);

        Client client = userRepository.findById(clientId).orElse(null);
        List<WishList> lists = client.getWishlist();
        Iterator<WishList> iterator = lists.iterator();
        while (iterator.hasNext()) {
            WishList list = iterator.next();
            if (list.getProductId().equals(productId)) {
                wishListRepo.delete(list);
                iterator.remove();
            }
        }
        userRepository.save(client);
    }

    public Client getClient(Long id) {
            Client client = userRepository.findById(id).orElse(null);
//        if(getCurrentUserId() != null) {
//            client = userRepository.findById(getCurrentUserId()).orElse(null);
//            if (client != null) {
//                return client;
//            }
//        }
//        client = new Client();
//        client.setUsername("Guest");
        return client;
    }

    public WishList addToWishList(Long productId, Long userId){
        WishList wish = new WishList();
        wish.setProductId(productId);
        wish.setUser(userRepository.findById(userId).orElse(null));
        return wishListRepo.save(wish);
    }

    public List<Order> getAllOrders() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<List<Order>> response = template.exchange(api.getOrder(),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Order>>(){});
        return response.getBody();
    }




}
