package com.example.demo.configs;

import com.example.demo.model.UserRole;
import com.example.demo.repository.GoodsRepository;
import com.example.demo.repository.UserOrderRepository;
import com.example.demo.service.GoodsService;
import com.example.demo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final GoodsRepository goodsRepository;
    private final UserOrderRepository userOrderRepository;

    public WebMvcConfig(GoodsRepository goodsRepository,
                        UserOrderRepository userOrderRepository) {
        this.goodsRepository = goodsRepository;
        this.userOrderRepository = userOrderRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/logout");
    }

   @Bean
    public CommandLineRunner start(final UserService userService, final GoodsService goodsService,
                                   final PasswordEncoder passwordEncoder) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                userService.addUser("admin", "admin", "0961471976",
                        "vitaliiriznyk@gmail.com",
                        passwordEncoder.encode("123456789"), UserRole.ADMIN);
                userService.addUser("user", "user", "0961471977",
                        "aaaddd@gmail.com",
                        passwordEncoder.encode("123456789"), UserRole.USER);
                goodsService.addGoodsToDB("Sweater",5000, "Sweater made from good cotton", null
                        //,null
                        );
                goodsService.addGoodsToDB("Green Rabbit", 600, "Really well for your kid", null
                        //,null
                        );
            }
        };
    }
}