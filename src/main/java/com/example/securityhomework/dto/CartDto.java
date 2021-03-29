package com.example.securityhomework.dto;

import com.example.securityhomework.beans.Cart;
import com.example.securityhomework.entities.OrderProduct;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CartDto {
    private List<OrderProductDto> items;
    private int totalPrice;

    public CartDto(Cart cart) {
        this.totalPrice = cart.getTotalPrice();
        this.items = cart.getItems().stream().map(OrderProductDto::new).collect(Collectors.toList());
    }

}
