package com.kiemthu.bai2;

import org.testng.annotations.Test;

public class CartTest {
    @Test(groups = {"smoke", "regression"})
    public void testAddToCart() {
        System.out.println("[CartTest] Chạy testAddToCart - Nhóm: smoke, regression");
    }

    @Test(groups = {"regression"})
    public void testRemoveFromCart() {
        System.out.println("[CartTest] Chạy testRemoveFromCart - Nhóm: regression");
    }
}