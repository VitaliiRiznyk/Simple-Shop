package com.example.demo.controller;

import com.example.demo.model.*;
import com.example.demo.repository.UserOrderRepository;
import com.example.demo.repository.GoodsRepository;
import com.example.demo.service.GoodsService;
import com.example.demo.service.SuccessOrderService;
import com.example.demo.service.UserOrderService;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class ProgController {
    private final GoodsRepository goodsRepository;
    private final SuccessOrderService successOrderService;
    private final UserOrderService userOrderService;
    private final GoodsService goodsService;

    @GetMapping("/")
    public String adminStart(Model model,
                             Authentication authentication) {
        if (authentication != null && authentication.getName().equals("admin")) {
            List<SuccessOrders> successOrdersList = successOrderService.findAll();
            model.addAttribute("successOrders", successOrdersList);
        }
        return "index";
    }

    @GetMapping("/buyings/{classification}")
    public String goBuy(@PathVariable String classification,
                        Model model) {
        List<Goods> iterable = goodsService.findGoodsByGoodsRole(classification);
        model.addAttribute("all", iterable);
        return "buyings";
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/buyings-user")
    public String addNewGood(@RequestParam Long goodsId,
                             Model model) {
        Goods goods = goodsRepository.findById(goodsId).get();
        model.addAttribute("buyGood", goods);
        return "buyings-user";
    }

    @PostMapping("/buyings-user")
    public String goodsOrderByUser(@RequestParam Long goodsId,
                                   @RequestParam String orderDetails,
                                   @RequestParam String addressOrder,
                                   @RequestParam Long orderQuantity,
                                   Authentication authentication) {
        userOrderService.addSuccessOrder(goodsId, orderDetails, addressOrder, orderQuantity, authentication);
        return "redirect:/";
    }
}