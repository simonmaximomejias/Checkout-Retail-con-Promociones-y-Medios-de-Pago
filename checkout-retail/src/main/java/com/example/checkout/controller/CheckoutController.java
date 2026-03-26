package com.example.checkout.controller;

import com.example.checkout.model.*;
import com.example.checkout.service.CheckoutService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    private final CheckoutService service;

    public CheckoutController(CheckoutService service) {
        this.service = service;
    }

    @PostMapping
    public CheckoutResponse checkout(@RequestBody CartRequest request) {
        return service.checkout(request);
    }
}