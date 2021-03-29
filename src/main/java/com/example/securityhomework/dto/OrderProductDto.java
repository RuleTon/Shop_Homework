package com.example.securityhomework.dto;

import com.example.securityhomework.entities.OrderProduct;

public class OrderProductDto {
    private String productTitle;
    private int quantity;
    private int pricePerProduct;
    private int price;

    public OrderProductDto(OrderProduct orderItem) {
        this.productTitle = orderItem.getProduct().getTitle();
        this.quantity = orderItem.getQuantity();
        this.pricePerProduct = orderItem.getPricePerProduct();
        this.price = orderItem.getPrice();
    }
}
