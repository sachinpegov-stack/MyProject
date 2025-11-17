package com.example.test.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class FormDetailsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // ---------- Page Locators ----------
    @FindBy(id = "payCommissionCode")
    private WebElement payCommissionDropdown;

    @FindBy(id = "cmbPnsnCatg")
    private WebElement pensionerTypeDropdown;

    @FindBy(id = "txtSevaarthId")
    private WebElement sevaarthIdField;

    @FindBy(id = "appDate")
    private WebElement applicationDateField;

    @FindBy(xpath = "//button[contains(@class, 'swal-button--confirm')]")
    private WebElement okButton;

    @FindBy(xpath = "//input[@id='radioDoWantCommuteYes']") // simplified XPath if possible
    private WebElement commynCheckbox;

    @FindBy(id = "tab1")
    private WebElement basicDetailsTab;

    // ---------- Constructor ----------
    public FormDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ---------- Page Actions ----------
    public void selectPayCommission(String payCommissionText) {
        wait.until(ExpectedConditions.visibilityOf(payCommissionDropdown));
        new Select(payCommissionDropdown).selectByVisibleText(payCommissionText);
    }

    public void selectPensionerType(String pensionerTypeText) {
        wait.until(ExpectedConditions.visibilityOf(pensionerTypeDropdown));
        new Select(pensionerTypeDropdown).selectByVisibleText(pensionerTypeText);
    }

    public void enterSevaarthId(String sevaarthId) {
        wait.until(ExpectedConditions.visibilityOf(sevaarthIdField));
        sevaarthIdField.clear();
        sevaarthIdField.sendKeys(sevaarthId);
    }

    public void enterApplicationDate(String date) {
        wait.until(ExpectedConditions.visibilityOf(applicationDateField));
        applicationDateField.clear();
        applicationDateField.sendKeys(date);
    }

    public void clickOkButton() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            // Wait for popup itself
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'swal-modal')]")
            ));

            // FIX — element may become stale, so re-locate before clicking
            WebElement okBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[contains(@class,'swal-button--confirm')]")
                    )
            );

            // Use JS Click — SweetAlert sometimes blocks normal click
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", okBtn);

            System.out.println("OK button clicked successfully");
        } catch (Exception e) {
            System.out.println("OK button not found, taking screenshot...");
            throw e;
        }
    }


    public void clickCommynCheckbox() {
        wait.until(ExpectedConditions.elementToBeClickable(commynCheckbox));
        if (!commynCheckbox.isSelected()) {
            commynCheckbox.click();
        }
    }

    public void goToBasicDetailsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(basicDetailsTab));
        basicDetailsTab.click();
    }

    // ---------- SweetAlert Handler ----------
    public void handleSweetAlerts() {
        try {
            FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(20))
                    .pollingEvery(Duration.ofMillis(500))
                    .ignoring(NoSuchElementException.class)
                    .ignoring(ElementClickInterceptedException.class);

            boolean alertsPresent = true;

            while (alertsPresent) {
                List<WebElement> alertButtons = fluentWait.until(new Function<WebDriver, List<WebElement>>() {
                    @Override
                    public List<WebElement> apply(WebDriver driver) {
                        List<WebElement> buttons = driver.findElements(By.xpath("//button[contains(@class, 'swal-button--confirm')]"));
                        return buttons.isEmpty() ? null : buttons;
                    }
                });

                if (alertButtons != null && !alertButtons.isEmpty()) {
                    for (WebElement btn : alertButtons) {
                        if (btn.isDisplayed() && btn.isEnabled()) {
                            btn.click();
                            System.out.println("✅ SweetAlert OK button clicked.");
                            Thread.sleep(500); // buffer for next alert
                        }
                    }
                } else {
                    alertsPresent = false;
                }
            }

        } catch (TimeoutException e) {
            System.out.println("✅ No more SweetAlert popups detected.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("⚠️ Interrupted during SweetAlert handling: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("⚠️ Error handling SweetAlert: " + e.getMessage());
        }
    }
}
