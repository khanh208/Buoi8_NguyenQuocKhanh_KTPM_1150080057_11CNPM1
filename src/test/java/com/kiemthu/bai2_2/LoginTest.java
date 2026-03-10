package com.kiemthu.bai2_2;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver("chrome"); 
    }

    @Test
    public void testLogin() throws InterruptedException {
        DriverFactory.getDriver().get("https://www.saucedemo.com/");
        System.out.println("[LoginTest] Đang chạy trên Thread ID: " + Thread.currentThread().getId());
        
        Thread.sleep(5000); 
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver(); 
    }
}