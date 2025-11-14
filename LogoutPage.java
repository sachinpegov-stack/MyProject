package com.example.test.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

public class LogoutPage {

    private WebDriver driver;

    // ---------- Constructor ----------
    public LogoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ---------- Page Actions ----------
    /**
     * Logs out from the application.
     */
    public void logout() throws InterruptedException {
        WebElement logoutBtn = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/header[1]/div[3]/nav[1]/div[1]/div[2]/ul[2]/li[1]/a[1]"));
        logoutBtn.click();
        Thread.sleep(2000);  // Wait for logout to complete
    }

    /**
     * Optionally, verify logout by checking page title or login button visibility
     */
    public boolean isLogoutSuccessful() {
        try {
            return driver.findElement(By.xpath("//input[@id='username' or @name='username']")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
