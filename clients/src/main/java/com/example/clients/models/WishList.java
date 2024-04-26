package com.example.clients.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "wish_list")
@Data
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Client user;

    private Long productId;



    @Override
    public String toString() {
        return "WishList{" +
                "id=" + id +
                ", productId=" + productId +
                '}';
    }
}
