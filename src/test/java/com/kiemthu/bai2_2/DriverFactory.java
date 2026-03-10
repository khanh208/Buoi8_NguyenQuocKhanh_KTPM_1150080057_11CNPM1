package com.kiemthu.bai2_2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static void initDriver(String browser) {
        WebDriver driver;
        switch (browser.toLowerCase()) {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                driver = new ChromeDriver(); 
        }
        driver.manage().window().maximize();
        tlDriver.set(driver); 
    }

    public static WebDriver getDriver() {
        return tlDriver.get(); 
    }

    public static void quitDriver() {
        if (tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove(); 
        }
    }
}