package com.kiemthu.bai2_2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    // ThreadLocal: đảm bảo mỗi luồng (thread) sẽ giữ một bản sao WebDriver riêng biệt
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static void initDriver(String browser) {
        WebDriver driver;
        switch (browser.toLowerCase()) {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                driver = new ChromeDriver(); // Mặc định dùng Chrome
        }
        driver.manage().window().maximize();
        tlDriver.set(driver); // Gắn driver vào luồng hiện tại
    }

    public static WebDriver getDriver() {
        return tlDriver.get(); // Lấy driver của luồng hiện tại ra dùng
    }

    public static void quitDriver() {
        if (tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove(); // Rất quan trọng: Xóa để tránh tràn bộ nhớ (memory leak)
        }
    }
}