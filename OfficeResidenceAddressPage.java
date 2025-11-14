package com.example.test.pages;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class OfficeResidenceAddressPage {

    private WebDriver driver;

    // ---------- Page Locators ----------
    @FindBy(id = "cmbHeadOfOff")
    private WebElement departmentDropdown;

    @FindBy(id = "cmbFieldDept")
    private WebElement subDepartmentDropdown;

    @FindBy(id = "txtOffFlatDoorBlk")
    private WebElement officeAddressField;

    @FindBy(id = "txtOffPincode")
    private WebElement officePincodeField;

    @FindBy(id = "txtPrFlatDoorBlk")
    private WebElement residenceAddressField;

    @FindBy(id = "residenceState")
    private WebElement stateDropdown;

    @FindBy(id = "residenceDistrict")
    private WebElement townDropdown;

    @FindBy(id = "txtPrPincode")
    private WebElement residencePincodeField;

    @FindBy(xpath = "//button[contains(@class, 'swal-button--confirm')]")
    private WebElement okButton;

    @FindBy(xpath = "/html[1]/body[1]/div[2]/fieldset[1]/form[1]/div[2]/div[1]/div[2]/div[1]/fieldset[1]/fieldset[4]/table[1]/tbody[1]/tr[1]/td[1]/input[1]")
    private WebElement yCheckBox;

    // ---------- Constructor ----------
    public OfficeResidenceAddressPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ---------- Page Actions ----------
    public void selectDepartment(String department) throws InterruptedException {
        Select select = new Select(departmentDropdown);
        select.selectByVisibleText(department);
        Thread.sleep(2000);
    }

    public void selectSubDepartment(String subDepartment) throws InterruptedException {
        Select select = new Select(subDepartmentDropdown);
        select.selectByVisibleText(subDepartment);
        Thread.sleep(2000);
    }

    public void enterOfficeAddress(String address, String pincode) {
        officeAddressField.clear();
        officeAddressField.sendKeys(address);

        officePincodeField.clear();
        officePincodeField.sendKeys(pincode);
    }

    public void enterResidenceAddress(String address, String state, String town, String pincode) throws InterruptedException {
        residenceAddressField.clear();
        residenceAddressField.sendKeys(address);

        Select stateSelect = new Select(stateDropdown);
        stateSelect.selectByVisibleText(state);

        Select townSelect = new Select(townDropdown);
        townSelect.selectByVisibleText(town);

        residencePincodeField.clear();
        residencePincodeField.sendKeys(pincode);
        Thread.sleep(1000);
    }

    public void clickOkButton() {
        okButton.click();
    }

    public void selectYCheckBox() {
        yCheckBox.click();
    }

    public void takeScreenshot(String path) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File(path));
    }

    /**
     * Convenience method to fill the full office & residence address form
     */
    public void fillAddressForm(String department, String subDepartment,
                                String officeAddr, String officePin,
                                String resAddr, String state, String town, String resPin) throws IOException, InterruptedException {
        selectDepartment(department);
        selectSubDepartment(subDepartment);
        enterOfficeAddress(officeAddr, officePin);
        enterResidenceAddress(resAddr, state, town, resPin);
        clickOkButton();
        selectYCheckBox();
        takeScreenshot("C:\\Users\\sachin.pawar\\Test Data\\screenshot3.png");
    }
}
