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

    @Enumerated(EnumType.STRING)
    private GoodsRole goodsRole;

    @Column(length = 2_000_000)
    private String decodedPhoto;

    public Goods(String goodsName, Integer goodsPrise, String aboutGoods, String decodedPhoto, GoodsRole goodsRole) {
        this.goodsName = goodsName;
        this.goodsPrise = goodsPrise;
        this.aboutGoods = aboutGoods;
        this.decodedPhoto = decodedPhoto;
        this.goodsRole = goodsRole;
    }

}