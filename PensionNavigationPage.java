package com.example.test.pages;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PensionNavigationPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // ---------- Locators ----------
    private By pensionMenu = By.xpath("//a[normalize-space()='Pension']");
    private By pensionSubMenu = By.xpath("//a[contains(text(),'Generate Pension Case')]");
    private By sevaarthiRadioButton = By.xpath("//input[@type='radio' and @value='2']");
    private By classOfPensionDropdown = By.id("cmbClassOfPnsn");
    private By proceedButton = By.id("procceed");

    // ---------- Constructor ----------
    public PensionNavigationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ---------- Utility Method ----------
    private void safeClick(WebElement element) {
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    private void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    // ---------- Page Actions ----------

    /** Navigate to Pension section */
    public void navigateToPensionSection() {
        WebElement menu = wait.until(ExpectedConditions.elementToBeClickable(pensionMenu));
        safeClick(menu);

        WebElement subMenu = wait.until(ExpectedConditions.elementToBeClickable(pensionSubMenu));
        scrollIntoView(subMenu);
        safeClick(subMenu);
    }

    /** Select Sevaarthi type */
    public void selectSevaarthi() {
        WebElement radio = wait.until(ExpectedConditions.elementToBeClickable(sevaarthiRadioButton));
        safeClick(radio);
    }

    /** Select pension type from dropdown */
    public void selectClassOfPension(String pensionType) {
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(classOfPensionDropdown));
        new Select(dropdown).selectByVisibleText(pensionType);
    }

    /** Click proceed */
    public void clickProceed() {
        WebElement proceed = wait.until(ExpectedConditions.elementToBeClickable(proceedButton));
        scrollIntoView(proceed);
        safeClick(proceed);
    }
}
