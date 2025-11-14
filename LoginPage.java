package com.example.test.pages;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // ---------- Page Locators ----------
    @FindBy(xpath = "//input[@name='username' or @id='username']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@name='password' or @id='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id='submit' or @type='submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[contains(@class,'error') or contains(text(),'Invalid')]")
    private WebElement errorMessage;

    // ---------- Constructor ----------
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // ---------- Page Actions ----------

    /**
     * Performs login using given credentials and captures a screenshot.
     */
    public void login(String username, String password, String screenshotPath) throws IOException {
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        usernameField.clear();
        usernameField.sendKeys(username);

        passwordField.clear();
        passwordField.sendKeys(password);

        // Take screenshot before submitting
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File(screenshotPath));

        // Handle captcha if script is available
        if (driver instanceof JavascriptExecutor executor) {
            try {
                executor.executeScript("enterCaptcha();");
            } catch (Exception e) {
                System.out.println("⚠️ Captcha script not found. Skipping...");
            }
        }

        loginButton.click();

        // Wait for login to complete (Dashboard or Home page)
		/*
		 * wait.until(ExpectedConditions.or(
		 * ExpectedConditions.titleContains("Dashboard"),
		 * ExpectedConditions.titleContains("Home") ));
		 */
    }

    /**
     * Returns true if login error message is visible.
     */
    public boolean isLoginErrorDisplayed() {
        try {
            return errorMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Returns current page title for verification.
     */
    public String getPageTitle() {
        return driver.getTitle();
    }
}
