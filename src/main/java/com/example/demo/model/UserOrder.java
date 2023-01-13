package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class UserOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String orderDetails;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private CustomUser customUser;
    @ManyToOne
    @JoinColumn(name = "goods_id")
    private Goods goods;

    public UserOrder(String orderDetails, CustomUser customUser, Goods goods) {
        this.orderDetails = orderDetails;
        this.customUser = customUser;
        this.goods = goods;
    }
}