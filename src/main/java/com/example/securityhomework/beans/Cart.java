package com.example.securityhomework.beans;

import com.example.securityhomework.entities.OrderProduct;
import com.example.securityhomework.entities.Product;
import com.example.securityhomework.exceptions.ResourceNoFound;
import com.example.securityhomework.services.ProductService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
@Component
public class Cart {
    private final ProductService productService;
    private List<OrderProduct> items;
    private int totalPrice;

    @PostConstruct
    public void init() {
        this.items = new ArrayList<>();
    }

    public void addToCart(Long id) {
        for (OrderProduct o : items) {
            if (o.getProduct().getId().equals(id)) {
                o.incrementQuantity();
                recalculate();
                return;
            }
        }
        Product p = productService.findProductById(id).orElseThrow(() -> new ResourceNoFound("Unable to find product with id: " + id + " (add to cart)"));
        OrderProduct orderItem = new OrderProduct(p);
        items.add(orderItem);
        recalculate();
    }

    public void clear() {
        items.clear();
        recalculate();
    }

    public void recalculate() {
        totalPrice = 0;
        for (OrderProduct o : items) {
            totalPrice += o.getPrice();
        }
    }
}
