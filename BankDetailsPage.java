package com.example.test.pages;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BankDetailsPage {

    private WebDriver driver;

    // ---------- Page Locators ----------
    @FindBy(id = "bankId")
    private WebElement bankNameDropdown;

    @FindBy(id = "cmbTargetBranchName")
    private WebElement bankBranchDropdown;

    @FindBy(id = "txtBankAddress")
    private WebElement bankAddressField;

    @FindBy(id = "txtActNo")
    private WebElement bankAccountField;

    @FindBy(xpath = "//button[contains(@class, 'swal-button--confirm')]")
    private WebElement okButton;

    // ---------- Constructor ----------
    public BankDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ---------- Page Actions ----------

    /**
     * Fills bank details form.
     */
    public void fillBankDetails(String bankName, String branchName, String bankAddress, String accountNumber)
            throws InterruptedException, IOException {
        // Select bank
        Select bankSelect = new Select(bankNameDropdown);
        bankSelect.selectByVisibleText(bankName);
        bankNameDropdown.getText(); // optional: can verify selection
        Thread.sleep(1000);

        // Select branch
        Select branchSelect = new Select(bankBranchDropdown);
        branchSelect.selectByVisibleText(branchName);
        bankBranchDropdown.getText();
        Thread.sleep(1000);

        // Enter bank address
        bankAddressField.clear();
        bankAddressField.sendKeys(bankAddress);

        // Enter account number
        bankAccountField.clear();
        bankAccountField.sendKeys(accountNumber);

        // Click OK to save
        okButton.click();
        Thread.sleep(1000);

        // Take screenshot
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File("C:\\Users\\sachin.pawar\\Test Data\\bankdetails.png"));
    }

    /**
     * Optional: check if bank details saved successfully (implementation depends on app)
     */
    public boolean isSaveSuccessful() {
        try {
            return okButton.isDisplayed() == false; // or some success message element
        } catch (Exception e) {
            return false;
        }
    }
}
