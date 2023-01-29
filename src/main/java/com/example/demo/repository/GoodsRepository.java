package com.example.demo.repository;

import com.example.demo.model.Goods;
import com.example.demo.model.GoodsRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods, Long> {
    @Query("select g from Goods g where g.goodsRole = ?1")
    List<Goods> findByRole(GoodsRole goodsRole);



}
