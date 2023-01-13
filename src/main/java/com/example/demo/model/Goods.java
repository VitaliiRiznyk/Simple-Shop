package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String goodsName;
    private Integer goodsPrise;
    private String aboutGoods;

    public Goods(String goodsName, Integer goodsPrise, String aboutGoods) {
        this.goodsName = goodsName;
        this.goodsPrise = goodsPrise;
        this.aboutGoods = aboutGoods;
    }
}