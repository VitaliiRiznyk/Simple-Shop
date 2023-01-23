package com.example.demo.controller;

import com.example.demo.model.UserOrder;
import com.example.demo.model.UserRole;
import com.example.demo.model.CustomUser;
import com.example.demo.model.Goods;
import com.example.demo.repository.UserOrderRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.GoodsRepository;
import com.example.demo.service.GoodsService;
import com.example.demo.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Controller
public class ProgController {

    private final GoodsRepository goodsRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserOrderRepository userOrderRepository;

    private final GoodsService goodsService;

    private final UserService userService;

    public ProgController(GoodsRepository goodsRepository,
                          PasswordEncoder passwordEncoder,
                          UserRepository userRepository,
                          UserOrderRepository userOrderRepository,
                          UserService userService,
                          GoodsService goodsService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.goodsRepository = goodsRepository;
        this.userOrderRepository = userOrderRepository;
        this.userService = userService;
        this.goodsService = goodsService;
    }

    @GetMapping("/")
    public String start() {
        return "index";
    }

    @GetMapping("/buyings")
    public String goBuy(Model model) {
        Iterable<Goods> iterable = goodsRepository.findAll();
        model.addAttribute("all", iterable);
        return "buyings";
    }

    @PostMapping("/buyings")
    public String userBuying(@RequestParam String goodsName,
                             @RequestParam Integer goodsPrise,
                             @RequestParam String aboutGoods) {
        Goods goods = new Goods(goodsName, goodsPrise, aboutGoods
                //, null
                ,null);
        goodsRepository.save(goods);
        return "redirect:/buyings";
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
        Goods goods = goodsRepository.findById(goodsId).get();
        CustomUser customUser = userRepository.findByLogin(authentication.getName());
        userOrderRepository.save(new UserOrder(orderDetails, addressOrder,
                orderQuantity, customUser, goods));
        return "redirect:/";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/current-orders")
    public String adminPageCurrentOrders(Model model) {
        List<UserOrder> currentOrders = userOrderRepository.findAll();
        model.addAttribute("orders", currentOrders);
        return "current-orders";
    }

    @PostMapping("/successOrder")
    public String successOrder(@RequestParam Long id) {
        userOrderRepository.deleteById(id);
        return "redirect:/current-orders";
    }

    @GetMapping("/sign-up")
    public String addNewUser() {
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String addNewUser(@RequestParam String login,
                             @RequestParam String userName,
                             @RequestParam String userPhone,
                             @RequestParam String email,
                             @RequestParam String password) {
        if (userRepository.findByLogin(login) != null) {
            return "redirect:/sign-up";
        }

        if (userService.addUser(login, userName, userPhone, email
                , passwordEncoder.encode(password), UserRole.USER)) {
            return "redirect:/login";
        }

        return "redirect:/";
    }

    @GetMapping("/admin-add")
    public String userGood(Model model) {
        return "admin";
    }

    @PostMapping("/admin-add")
    public String addGoods(@RequestParam String goodsName,
                           @RequestParam Integer goodsPrise,
                           @RequestParam String aboutGoods,
                           @RequestParam("photoGoods") MultipartFile multipartFile) throws IOException {
        byte [] photoGoods = multipartFile.getBytes();
        String decodedPhoto=Base64.getEncoder().encodeToString(photoGoods);
        goodsService.addGoodsToDB(goodsName, goodsPrise, aboutGoods
                //, photoGoods
                ,decodedPhoto);
        return "redirect:/";
    }
}