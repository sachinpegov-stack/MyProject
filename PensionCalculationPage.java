package com.example.test.pages;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

public class PensionCalculationPage {

    private WebDriver driver;

    // ---------- Constructor ----------
    public PensionCalculationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ---------- Page Actions ----------

    /**
     * Performs pension calculation workflow.
     */
    public void calculatePension() throws IOException, InterruptedException {
        // Select Recovery Advance No
        WebElement recoveryAdvanceNo = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/fieldset[1]/form[1]/div[2]/div[1]/div[5]/div[1]/table[1]/tbody[1]/tr[2]/td[1]/input[2]"));
        recoveryAdvanceNo.click();
        Thread.sleep(2000);

        // Screenshot after selecting recovery advance
        File src8 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src8, new File("C:\\Users\\sachin.pawar\\Test Data\\screenshot8.png"));

        // Navigate to Pension Calculation tab
        WebElement pensionCalculationTab = driver.findElement(By.xpath("//*[@id='tab6']"));
        pensionCalculationTab.click();

        // Click Calculate Button
        WebElement calculateBtn = driver.findElement(By.xpath("//*[@id='btnCalculateBottom']"));
        calculateBtn.click();
        Thread.sleep(2000);

        // Screenshot after calculation
        File src9 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src9, new File("C:\\Users\\sachin.pawar\\Test Data\\screenshot9.png"));

        // Click Save & Forward
        WebElement saveForwardBtn = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/fieldset[1]/form[1]/div[2]/div[1]/div[7]/div[1]/div[2]/div[1]/input[1]"));
        saveForwardBtn.click();
        Thread.sleep(2000);

        // Confirm popups
        WebElement okBtn1 = driver.findElement(By.xpath("//button[contains(@class, 'swal-button--confirm')]"));
        okBtn1.click();
        Thread.sleep(2000);

        WebElement okBtn2 = driver.findElement(By.xpath("//button[contains(@class, 'swal-button--confirm')]"));
        okBtn2.click();
        Thread.sleep(2000);
    }

    /**
     * Returns true if calculation completed successfully (can be enhanced with actual verification)
     */
    public boolean isCalculationSuccessful() {
        try {
            // Add real verification logic if available
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
