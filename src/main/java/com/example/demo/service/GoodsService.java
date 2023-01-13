package com.example.demo.service;

import com.example.demo.model.Goods;
import com.example.demo.repository.GoodsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GoodsService {

    private final GoodsRepository goodsRepository;

    public GoodsService(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    @Transactional
    public boolean addGoods(String goodsName, Integer goodsPrise, String aboutGoods){
        Goods goods = new Goods(goodsName, goodsPrise, aboutGoods);
        goodsRepository.save(goods);
        return true;
    }

}