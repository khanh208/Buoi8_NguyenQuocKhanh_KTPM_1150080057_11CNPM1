package com.kiemthu.bai2;

import org.testng.annotations.Test;

public class LoginTest {
    @Test(groups = {"smoke", "regression"})
    public void testLoginSuccess() {
        System.out.println("[LoginTest] Chạy testLoginSuccess - Nhóm: smoke, regression");
    }

    @Test(groups = {"regression"})
    public void testLoginWrongPassword() {
        System.out.println("[LoginTest] Chạy testLoginWrongPassword - Nhóm: regression");
    }
}