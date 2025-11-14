package com.example.test.pages;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class PayServiceDetailsPage {

    private WebDriver driver;

    // ---------- Constructor ----------
    public PayServiceDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ---------- Page Actions ----------

    /**
     * Fills Pay Service Details form.
     */
    public void fillPayServiceDetails(String payScale, String fromDate, String avgPayBasic) 
            throws IOException, InterruptedException {

        // Select Pay Scale
        WebElement payscale = driver.findElement(By.xpath("//*[@id='cmbPayScale']"));
        Select payscaleselect = new Select(payscale);
        payscaleselect.selectByVisibleText(payScale);
        WebElement payscaleelement = payscaleselect.getFirstSelectedOption();

        // Screenshot after selecting Pay Scale
        File src5 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src5, new File("C:\\Users\\sachin.pawar\\Test Data\\screenshot5.png"));

        // Enter From Date
        WebElement fromdate = driver.findElement(By.xpath("//*[@id='txtEmolumentFromDate']"));
        fromdate.sendKeys(fromDate);

        // Click OK
        WebElement okbtn2 = driver.findElement(By.xpath("//button[contains(@class, 'swal-button--confirm')]"));
        okbtn2.click();

        // Enter Average Pay Basic
        WebElement payband = driver.findElement(By.xpath("//*[@id='txtAvgPayBasic0']"));
        payband.sendKeys(avgPayBasic);
        Thread.sleep(2000);

        // Screenshot after entering PayBand
        File src6 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src6, new File("C:\\Users\\sachin.pawar\\Test Data\\screenshot6.png"));

        // Navigate to Family Details tab
        WebElement familydtlstab = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/fieldset[1]/form[1]/div[2]/div[1]/ul[1]/li[4]/a[1]"));
        familydtlstab.click();
    }
}
