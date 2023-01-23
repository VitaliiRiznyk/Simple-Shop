package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Base64;

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

   /*@Column(nullable = true)
    @Lob
    private byte[] photoGoods;*/

    @Column(length = 2_000_000)
    private String decodedPhoto;

    public Goods(String goodsName, Integer goodsPrise, String aboutGoods//, byte[] photoGoods
                 ,String decodedPhoto) {
        this.goodsName = goodsName;
        this.goodsPrise = goodsPrise;
        this.aboutGoods = aboutGoods;
        //this.photoGoods = photoGoods;
        this.decodedPhoto = decodedPhoto;
    }

}