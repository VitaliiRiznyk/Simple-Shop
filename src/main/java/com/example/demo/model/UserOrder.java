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

    private Long orderQuantity;

    private String addressOrder;

    private Long orderSummaryPrice;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private CustomUser customUser;
    @ManyToOne
    @JoinColumn(name = "goods_id")
    private Goods goods;

    public UserOrder(String orderDetails, String addressOrder, Long orderQuantity, CustomUser customUser, Goods goods, Long orderSummaryPrice) {
        this.orderDetails = orderDetails;
        this.addressOrder = addressOrder;
        this.orderQuantity = orderQuantity;
        this.customUser = customUser;
        this.goods = goods;
        this.orderSummaryPrice = orderSummaryPrice;
    }
}