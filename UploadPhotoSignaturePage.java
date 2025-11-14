package com.example.test.pages;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class UploadPhotoSignaturePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // ---- Locators ----
    @FindBy(id = "descPhoto")
    private WebElement photoDescription;

    @FindBy(id = "descSign")
    private WebElement signDescription;

    @FindBy(id = "importFilePhoto")
    private WebElement photoUpload;

    @FindBy(id = "importFileSign")
    private WebElement signUpload;

	/*
	 * @FindBy(xpath = "//button[contains(@class,'swal-button--confirm')]") private
	 * WebElement okButton;
	 */

    @FindBy(xpath = "/html/body/div[2]/fieldset/form/div[2]/div/ul/li[3]/a")
    private WebElement payServiceTab;

    public UploadPhotoSignaturePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void uploadPhotoAndSignature(String photoDesc, String signDesc,
                                        String photoPath, String signPath) throws IOException {

        photoDescription.sendKeys(photoDesc);
        signDescription.sendKeys(signDesc);

        photoUpload.sendKeys(photoPath);
        signUpload.sendKeys(signPath);

        // Take screenshot
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File("C:\\Users\\sachin.pawar\\Test Data\\screenshot4.png"));

		/*
		 * // Wait for popup and click OK
		 * wait.until(ExpectedConditions.elementToBeClickable(okButton)).click();
		 * 
		 * // Wait for popup to disappear
		 * wait.until(ExpectedConditions.invisibilityOf(okButton));
		 */
        // Click Pay Service tab
        wait.until(ExpectedConditions.elementToBeClickable(payServiceTab)).click();
    }
}
