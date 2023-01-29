package com.example.demo.service;

import com.example.demo.model.Goods;
import com.example.demo.model.GoodsRole;
import com.example.demo.repository.GoodsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GoodsService {

    private final GoodsRepository goodsRepository;

    public GoodsService(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    @Transactional
    public boolean addGoodsToDB(String goodsName, Integer goodsPrise, String aboutGoods
            , String decodedPhoto, GoodsRole goodsRole){
        Goods goods = new Goods(goodsName, goodsPrise, aboutGoods
                ,decodedPhoto, goodsRole);
        goodsRepository.save(goods);
        return true;
    }
    @Transactional
    public List<Goods> findGoodsByGoodsRole(String goodsRole){
        return goodsRepository.findByRole(GoodsRole.valueOf(goodsRole.toUpperCase()));
    }
}