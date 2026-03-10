package com.kiemthu.bai1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest {
    private WebDriver driver;
    private WebDriverWait wait;

    // Bộ định vị (Locators)
    private final By txtUsername = By.id("user-name");
    private final By txtPassword = By.id("password");
    private final By btnLogin = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    @BeforeMethod
    public void setUp() {
        // Khởi tạo trình duyệt mới trước mỗi Test Case
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
    }

    @AfterMethod
    public void tearDown() {
        // Đóng trình duyệt sau mỗi Test Case
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testLoginSuccess() {
        driver.findElement(txtUsername).sendKeys("standard_user");
        driver.findElement(txtPassword).sendKeys("secret_sauce");
        driver.findElement(btnLogin).click();

        // Dùng Explicit Wait chờ URL chuyển sang /inventory.html
        wait.until(ExpectedConditions.urlContains("/inventory.html"));
        
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://www.saucedemo.com/inventory.html", "Lỗi: Đăng nhập thành công nhưng không chuyển hướng đúng trang inventory.");
    }

    @Test
    public void testLoginWrongPassword() {
        driver.findElement(txtUsername).sendKeys("standard_user");
        driver.findElement(txtPassword).sendKeys("wrong_password");
        driver.findElement(btnLogin).click();

        // Chờ thông báo lỗi hiển thị
        WebElement errorMsgElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        String actualMessage = errorMsgElement.getText();
        
        Assert.assertEquals(actualMessage, "Epic sadface: Username and password do not match any user in this service", "Lỗi: Thông báo sai mật khẩu không hiển thị đúng.");
    }

    @Test
    public void testLoginEmptyUsername() {
        driver.findElement(txtPassword).sendKeys("secret_sauce");
        driver.findElement(btnLogin).click();

        WebElement errorMsgElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        String actualMessage = errorMsgElement.getText();
        
        Assert.assertEquals(actualMessage, "Epic sadface: Username is required", "Lỗi: Thông báo bỏ trống Username không đúng.");
    }

    @Test
    public void testLoginEmptyPassword() {
        driver.findElement(txtUsername).sendKeys("standard_user");
        driver.findElement(btnLogin).click();

        WebElement errorMsgElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        String actualMessage = errorMsgElement.getText();
        
        Assert.assertEquals(actualMessage, "Epic sadface: Password is required", "Lỗi: Thông báo bỏ trống Password không đúng.");
    }

    @Test
    public void testLoginLockedUser() {
        driver.findElement(txtUsername).sendKeys("locked_out_user");
        driver.findElement(txtPassword).sendKeys("secret_sauce");
        driver.findElement(btnLogin).click();

        WebElement errorMsgElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        String actualMessage = errorMsgElement.getText();
        
        Assert.assertEquals(actualMessage, "Epic sadface: Sorry, this user has been locked out.", "Lỗi: Thông báo user bị khóa không chính xác.");
    }
}