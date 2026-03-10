package com.kiemthu.bai2;

import org.testng.annotations.Test;

public class CheckoutTest {
    @Test(groups = {"smoke", "regression"})
    public void testCheckoutSuccess() {
        System.out.println("[CheckoutTest] Chạy testCheckoutSuccess - Nhóm: smoke, regression");
    }

    @Test(groups = {"regression"})
    public void testCancelCheckout() {
        System.out.println("[CheckoutTest] Chạy testCancelCheckout - Nhóm: regression");
    }
}