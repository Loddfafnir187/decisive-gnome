package com.example.storage.services;

import com.example.order.models.Order;
import com.example.storage.models.Product;
import com.example.storage.models.exceptions.ExcessAmountException;
import com.example.storage.models.exceptions.ResourceNotFoundException;
import com.example.storage.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

//    Получить список всех товаров
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

//    Получить товар по ID
    public Product getById(Long id) throws ResourceNotFoundException {
        return productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Товар " + id + " не найден"));
    }

//    Уменьшить количество оставшегося товара
    @Transactional
    public void reduceAmount(@PathVariable Long id, Order order) throws ExcessAmountException {
        Product product = getById(id);
        if (order.getQuantity() > product.getQuantity()) {
            throw new ExcessAmountException("Заказ превышает остаток на складе!");
        }
        product.setQuantity(product.getQuantity() - order.getQuantity());;
        productRepository.save(product);
    }

//    Зарезервировать товар
    @Transactional
    public void reserveProduct(Long id, int amount) throws ExcessAmountException {
        Product product = getById(id);
        if (amount > product.getQuantity()) {
            throw new ExcessAmountException("Заказ превышает остаток на складе!");
        }
        product.setReserved(product.getReserved() + amount);
        productRepository.save(product);
    }

//    Откат операции резервирования
    public void rollbackReserve(Long id, int amount) {
        Product product = getById(id);
        product.setReserved(product.getReserved() - amount);
        productRepository.save(product);
    }
}
