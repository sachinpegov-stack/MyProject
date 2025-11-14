package com.example.test.pages;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BasicDetailsPage {

    private WebDriver driver;

    // ---------- Page Locators ----------
    @FindBy(id = "txtPnsnrName")
    private WebElement pensionerNameField;

    @FindBy(id = "radioMaleFemale")
    private WebElement genderRadio;

    @FindBy(id = "txtHeight")
    private WebElement heightField;

    @FindBy(id = "txtInches")
    private WebElement inchesField;

    @FindBy(id = "txtDateOfBirth")
    private WebElement dateOfBirthField;

    @FindBy(id = "txtDateOfStartingService")
    private WebElement joiningDateField;

    @FindBy(id = "txtDateOfRetiremt")
    private WebElement retirementDateField;

    @FindBy(id = "empDesg")
    private WebElement designationDropdown;

    @FindBy(id = "religionCode")
    private WebElement religionDropdown;

    @FindBy(id = "empClass")
    private WebElement groupDropdown;

    @FindBy(xpath = "//button[contains(@class, 'swal-button--confirm')]")
    private WebElement okButton;

    // ---------- Constructor ----------
    public BasicDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ---------- Page Actions ----------

    public void enterPensionerName(String name) {
        pensionerNameField.clear();
        pensionerNameField.sendKeys(name);
    }

    public void selectGender() {
        genderRadio.click();
    }

    public void enterHeight(String feet, String inches) {
        heightField.clear();
        heightField.sendKeys(feet);
        inchesField.clear();
        inchesField.sendKeys(inches);
    }

    public void enterDateOfBirth(String dob) {
        dateOfBirthField.clear();
        dateOfBirthField.sendKeys(dob);
    }

    public void enterJoiningDate(String joiningDate) {
        joiningDateField.clear();
        joiningDateField.sendKeys(joiningDate);
    }

    public void enterRetirementDate(String retireDate) {
        retirementDateField.clear();
        retirementDateField.sendKeys(retireDate);
    }

    public void selectDesignation(String designation) {
        Select select = new Select(designationDropdown);
        select.selectByVisibleText(designation);
    }

    public void selectReligion(String religion) {
        Select select = new Select(religionDropdown);
        select.selectByVisibleText(religion);
    }

    public void selectGroup(String group) {
        Select select = new Select(groupDropdown);
        select.selectByVisibleText(group);
    }

    public void clickOkButton() {
        okButton.click();
    }

    public void takeScreenshot(String path) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File(path));
    }

    /**
     * Fill all basic details in one method (optional convenience method)
     */
    public void fillBasicDetails(String name, String dob, String gender,
                                 String designation, String religion, String group,
                                 String heightFeet, String heightInches,
                                 String joiningDate, String retireDate) throws IOException {
        enterPensionerName(name);
        selectGender();
        enterHeight(heightFeet, heightInches);
        enterDateOfBirth(dob);
        enterJoiningDate(joiningDate);
        enterRetirementDate(retireDate);
        selectDesignation(designation);
        selectReligion(religion);
        selectGroup(group);
        clickOkButton();
        takeScreenshot("C:\\Users\\sachin.pawar\\Test Data\\screenshot2.png");
    }
}
