package com.example.test.pages;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

public class BasicDetailsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // ---------- WAIT SAFE GET ELEMENT ----------
    private WebElement waitFor(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    private WebElement waitToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // ---------- Locators ----------
    @FindBy(id = "txtPnsnrName")
    private WebElement pensionerNameField;

    @FindBy(xpath = "//input[@name='radioMaleFemale' and @value='Male']")
    private WebElement genderMaleRadio;

    @FindBy(xpath = "//input[@name='radioMaleFemale' and @value='Female']")
    private WebElement genderFemaleRadio;

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
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    // ---------- Actions with Waits ----------
    public void enterPensionerName(String name) {
        waitFor(pensionerNameField).clear();
        pensionerNameField.sendKeys(name);
    }

    public void selectGender(String gender) {
        if (gender.equalsIgnoreCase("Male")) {
            waitToBeClickable(genderMaleRadio).click();
        } else {
            waitToBeClickable(genderFemaleRadio).click();
        }
    }

    public void enterHeight(String feet, String inches) {
        waitFor(heightField).clear();
        heightField.sendKeys(feet);

        waitFor(inchesField).clear();
        inchesField.sendKeys(inches);
    }

    public void enterDateOfBirth(String dob) {
        waitFor(dateOfBirthField).clear();
        dateOfBirthField.sendKeys(dob);
    }

    public void enterJoiningDate(String joiningDate) {
        waitFor(joiningDateField).clear();
        joiningDateField.sendKeys(joiningDate);
    }

    public void enterRetirementDate(String retireDate) {
        waitFor(retirementDateField).clear();
        retirementDateField.sendKeys(retireDate);
    }

    public void selectDesignation(String designation) {
        Select select = new Select(waitFor(designationDropdown));
        select.selectByVisibleText(designation);
    }

    public void selectReligion(String religion) {
        Select select = new Select(waitFor(religionDropdown));
        select.selectByVisibleText(religion);
    }

    public void selectGroup(String group) {
        Select select = new Select(waitFor(groupDropdown));
        select.selectByVisibleText(group);
    }

    public void clickOkButton() {
        try {
            waitToBeClickable(okButton).click();
        } catch (Exception e) {
            // fallback if popup is slow
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", okButton);
        }
    }

    public void takeScreenshot(String path) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File(path));
    }

    // ---------- Main method to fill all details ----------
    public void fillBasicDetails(String name, String dob, String gender,
                                 String designation, String religion, String group,
                                 String heightFeet, String heightInches,
                                 String joiningDate, String retireDate) throws IOException {

        enterPensionerName(name);
        selectGender(gender);
        enterHeight(heightFeet, heightInches);
        enterDateOfBirth(dob);
        enterJoiningDate(joiningDate);
        enterRetirementDate(retireDate);
        selectDesignation(designation);
        selectReligion(religion);
        selectGroup(group);
        clickOkButton();
        takeScreenshot("C:\\Users\\sachin.pawar\\Test Data\\screenshot_basic_details.png");
    }
}
