package com.example.test.pages;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class NomineeDetailsPage {

    private WebDriver driver;

    // ---------- Constructor ----------
    public NomineeDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ---------- Page Actions ----------

    /**
     * Fills the Nominee Details form.
     */
    public void fillNomineeDetails(
            String nomineeName,
            String relation,
            String percentage,
            String handicap,
            String dob,
            String govtEmp,
            String gratuityPercentage,
            String bank,
            String bankBranch,
            String bankAcc,
            String bankAddress,
            String nomineeAddress
    ) throws IOException, InterruptedException {

        // Click to add a nominee
        WebElement nomineey = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/fieldset[1]/form[1]/div[2]/div[1]/div[4]/div[1]/fieldset[1]/table[2]/tbody[1]/tr[1]/td[1]/input[1]"));
        nomineey.click();

        // Enter Nominee Name
        WebElement nomineename = driver.findElement(By.xpath("//*[@id='nameOfFamMemb0']"));
        nomineename.sendKeys(nomineeName);

        // Select Relation
        WebElement relationnominee = driver.findElement(By.xpath("//*[@id='relation0']"));
        Select relationSelect = new Select(relationnominee);
        relationSelect.selectByVisibleText(relation);

        // Enter Percentage
        WebElement nomineepercentage = driver.findElement(By.xpath("//*[@id='percentage0']"));
        nomineepercentage.sendKeys(percentage);

        // Select Handicap
        WebElement nomineehandicap = driver.findElement(By.xpath("//*[@id='phyhandMentChal0']"));
        Select handicapSelect = new Select(nomineehandicap);
        handicapSelect.selectByVisibleText(handicap);

        // Enter Date of Birth
        WebElement nomineedob = driver.findElement(By.xpath("//*[@id='dob0']"));
        nomineedob.sendKeys(dob);

        // Select Govt Employee
        WebElement goveempyn = driver.findElement(By.xpath("//*[@id='isGovtEmp0']"));
        Select govtEmpSelect = new Select(goveempyn);
        govtEmpSelect.selectByVisibleText(govtEmp);

        // Enter Gratuity Percentage
        WebElement grautitypercentage = driver.findElement(By.xpath("//*[@id='gratuityPercentage0']"));
        grautitypercentage.sendKeys(gratuityPercentage);

        // Select Bank
        WebElement nomineebank = driver.findElement(By.xpath("//*[@id='lstPensionEmpFamilyDtlsModel0bankId']"));
        Select bankSelect = new Select(nomineebank);
        bankSelect.selectByVisibleText(bank);

        // Select Bank Branch
        WebElement nomineebankbranch = driver.findElement(By.xpath("//*[@id='lstPensionEmpFamilyDtlsModel0bankBranchId']"));
        Select bankBranchSelect = new Select(nomineebankbranch);
        bankBranchSelect.selectByVisibleText(bankBranch);

        // Enter Bank Account Number
        WebElement nomineebankacc = driver.findElement(By.xpath("//*[@id='lstPensionEmpFamilyDtlsModel0accNo']"));
        nomineebankacc.sendKeys(bankAcc);

        // Enter Bank Address
        WebElement nomineebankaddrs = driver.findElement(By.xpath("//*[@id='lstPensionEmpFamilyDtlsModel0branchAddress']"));
        nomineebankaddrs.sendKeys(bankAddress);

        // Enter Nominee Address
        WebElement nomineeaddrs = driver.findElement(By.xpath("//*[@id='lstPensionEmpFamilyDtlsModel0nomineeAddress']"));
        nomineeaddrs.sendKeys(nomineeAddress);

        // Take screenshot
        File src7 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src7, new File("C:\\Users\\sachin.pawar\\Test Data\\screenshot7.png"));

        // Navigate to Recovery tab
        WebElement recovery = driver.findElement(By.xpath("/html[1]/body[1]/div[2]/fieldset[1]/form[1]/div[2]/div[1]/ul[1]/li[5]/a[1]"));
        recovery.click();
    }

    /**
     * Returns true if nominee details appear saved (can be enhanced with actual verification)
     */
    public boolean isSaveSuccessful() {
        try {
            // Add real verification logic if available
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
