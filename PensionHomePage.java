package com.example.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PensionHomePage {

    private WebDriver driver;

    // ---------- Page Locators ----------
    @FindBy(xpath = "//a[contains(text(),'Pension Case')]")
    private WebElement pensionCaseLink;

    @FindBy(xpath = "//a[contains(text(),'Basic Details')]")
    private WebElement basicDetailsTab;

    @FindBy(xpath = "//a[contains(text(),'Bank Details')]")
    private WebElement bankDetailsTab;

    @FindBy(xpath = "//a[contains(text(),'Nominee Details')]")
    private WebElement nomineeDetailsTab;

    @FindBy(xpath = "//a[contains(text(),'Submit')]")
    private WebElement submitButton;

    // ---------- Constructor ----------
    public PensionHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ---------- Actions ----------

    public void openPensionCase() {
        pensionCaseLink.click();
        System.out.println("📂 Opened Pension Case section");
    }

    public void openBasicDetails() {
        basicDetailsTab.click();
        System.out.println("🧾 Opened Basic Details tab");
    }

    public void openBankDetails() {
        bankDetailsTab.click();
        System.out.println("🏦 Opened Bank Details tab");
    }

    public void openNomineeDetails() {
        nomineeDetailsTab.click();
        System.out.println("👨‍👩‍👧‍👦 Opened Nominee Details tab");
    }

    public void submitCase() {
        submitButton.click();
        System.out.println("✅ Pension Case submitted");
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
