package com.example.clients.repositories;

import com.example.clients.models.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepo extends JpaRepository<WishList, Long> {
}
