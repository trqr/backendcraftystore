package com.beboustore.demo.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name= "product_id")
    private Product product;
    private double productPrice;
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name= "order_id")
    @JsonBackReference
    private Order order;

    public void setOrder(Order order) {
        this.order = order;
    }
}
