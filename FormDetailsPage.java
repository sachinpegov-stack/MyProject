package com.example.test.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

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

    @FindBy(xpath = "/html[1]/body[1]/div[2]/fieldset[1]/form[1]/div[2]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/table[1]/tbody[1]/tr[6]/td[2]/input[1]")
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
        Select paySelect = new Select(payCommissionDropdown);
        paySelect.selectByVisibleText(payCommissionText);
    }

    public void selectPensionerType(String pensionerTypeText) {
        wait.until(ExpectedConditions.visibilityOf(pensionerTypeDropdown));
        Select pensionerSelect = new Select(pensionerTypeDropdown);
        pensionerSelect.selectByVisibleText(pensionerTypeText);
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
        wait.until(ExpectedConditions.elementToBeClickable(okButton));
        okButton.click();
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
}
