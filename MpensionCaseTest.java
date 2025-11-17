package com.example.test.pages;

import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.example.test.base.BaseClass;
import com.example.test.utils.ConfigReader;

public class MpensionCaseTest extends BaseClass {

    private Properties prop;

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setup(@Optional("chrome") String browser) {
        System.out.println("🟦 Starting setup for: " + browser);
        prop = ConfigReader.initProperties();
        launchBrowser(browser);
        getDriver().get(prop.getProperty("url"));
    }


    @Test
    public void verifyPensionFlow() throws IOException {
        System.out.println("🚀 Starting Pension Flow Test...");

        // ---------- Login ----------
        LoginPage loginPage = new LoginPage(getDriver());
        String screenshotPath = prop.getProperty("screenshotPath", "C:\\Users\\sachin.pawar\\Test Data\\screenshot_" + System.currentTimeMillis() + ".png");
        loginPage.login(prop.getProperty("username", "54321_PCLK"),
                        prop.getProperty("password", "Password@123"),
                        screenshotPath);

        String title = loginPage.getPageTitle();
        System.out.println("✅ Page Title: " + title);
		/*
		 * Assert.assertTrue(title.contains("Dashboard") || title.contains("Home"),
		 * "❌ Login may have failed — unexpected page title: " + title);
		 */
        
     // ---------- Pension Navigation ----------
        PensionNavigationPage pensionNav = new PensionNavigationPage(getDriver());
        pensionNav.navigateToPensionSection();
        pensionNav.selectSevaarthi();
        pensionNav.selectClassOfPension("Superannuation Pension");
        pensionNav.clickProceed();
        System.out.println("✅ Navigated and selected Pension type successfully");
        
        
     // ---------- Form Details ----------
        FormDetailsPage formDetails = new FormDetailsPage(getDriver());

        formDetails.selectPayCommission("Seventh Pay Commission");
        formDetails.selectPensionerType("Group D");
        formDetails.enterSevaarthId("MJPSDPM9601");
        formDetails.enterApplicationDate("01112024");
        formDetails.clickOkButton();
        formDetails.clickCommynCheckbox();
        formDetails.goToBasicDetailsTab();

        System.out.println("✅ Form details filled successfully");



     // ---------- Basic Details ----------
        BasicDetailsPage basicDetailsPage = new BasicDetailsPage(getDriver());

        // Generate a dynamic screenshot path
        String screenshotPath1 = "C:\\Users\\sachin.pawar\\Test Data\\screenshot_basic_details_"
                                + System.currentTimeMillis() + ".png";

        // Fill the basic details using the Page Object
        basicDetailsPage.fillBasicDetails(
            "Sagar Namdev Hajare",      // Name
            "01011965",                     // DOB
            "Male",                         // Gender
            "Junior Clerk",                 // Designation
            "HINDU",                        // Religion
            "D",                            // Group
            "5", "5",                       // Height Feet & Inches
            "01011991",                      // Joining Date
            "31052024"
        );

        System.out.println("✅ Basic details filled successfully. Screenshot saved at: " + screenshotPath1);

     // ---------- Office & Residence Address ----------
        try {
            OfficeResidenceAddressPage addressPage = new OfficeResidenceAddressPage(getDriver());
            addressPage.fillAddressForm(
                "MAHARASHTRA",          // Department
                "HEAD OFFICE DEMO",     // Sub Department
                "Demo office",          // Office Address
                "452136",               // Office Pincode
                "Thane west",           // Residence Address
                "MAHARASHTRA",          // State
                "THANE",                // Town
                "452136"                // Residence Pincode
            );
            System.out.println("✅ Office & Residence Address filled successfully");
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            System.out.println("❌ Failed to fill Office & Residence Address");
        }

        //---------------BankDetailsPage-----------------

        BankDetailsPage bankDetailsPage = new BankDetailsPage(getDriver());
        try {
            bankDetailsPage.fillBankDetails(
                "BANK OF MAHARASHTRA",
                "BHIWANDI,THANE,THANE",
                "Thane west",
                "56468998761311"
            );
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            Assert.fail("❌ Failed to fill bank details due to exception: " + e.getMessage());
        }

        if (bankDetailsPage.isSaveSuccessful()) {
            System.out.println("✅ Bank details saved successfully");
        } else {
            System.out.println("❌ Failed to save bank details");
        }

        //----------- UploadPhotoSignaturePage---------------
        UploadPhotoSignaturePage uploadPage = new UploadPhotoSignaturePage(getDriver());
        try {
            uploadPage.uploadPhotoAndSignature(
                "abc",                        
                "abc",                        
                "D:\\Download\\boy images.jpg", 
                "D:\\Download\\signs.jpg"
            );
            System.out.println("✅ Photo and Signature uploaded successfully");
        } catch (IOException e) {  // only IOException now
            e.printStackTrace();
            Assert.fail("❌ Failed to upload photo/signature: " + e.getMessage());
        }


        
        //----------------PayServiceDetailsPage---------------
        
        PayServiceDetailsPage payServicePage = new PayServiceDetailsPage(getDriver());
        try {
            payServicePage.fillPayServiceDetails(
                "21700-69100,S_7", // Pay Scale
                "01082022",        // From Date
                "31500"            // Average Pay Basic
            );
            System.out.println("✅ Pay Service details filled successfully");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            Assert.fail("❌ Failed to fill Pay Service details: " + e.getMessage());
        }


        // ---------- Nominee Details ----------
        NomineeDetailsPage nomineePage = new NomineeDetailsPage(getDriver());
        try {
            nomineePage.fillNomineeDetails(
                "Bhagyshree Pratik Shinde",  // Name
                "Wife",                      // Relation
                "100",                        // Percentage
                "No",                         // Handicap
                "01011975",                   // DOB
                "No",                         // Govt Employee
                "100",                        // Gratuity %
                "BANK OF INDIA",              // Bank
                "PUNE CITY, BUDHWAR PETH",   // Bank Branch
                "5478975463265",              // Bank Account
                "THANE WEST",                 // Bank Address
                "Thane"                       // Nominee Address
            );
            System.out.println("✅ Nominee details filled successfully");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            Assert.fail("❌ Failed to fill Nominee details: " + e.getMessage());
        }
    
    //-------------PensionCalculationPage----------------------
    PensionCalculationPage pensionCalcPage = new PensionCalculationPage(getDriver());
    try {
        pensionCalcPage.calculatePension();
        System.out.println("✅ Pension calculation completed successfully");
    } catch (IOException | InterruptedException e) {
        e.printStackTrace();
        Assert.fail("❌ Pension calculation failed: " + e.getMessage());
    }
    
    //----------LogoutPage---------------

    LogoutPage logoutPage = new LogoutPage(getDriver());
    try {
        logoutPage.logout();
        System.out.println("✅ Logout successful");
    } catch (InterruptedException e) {
        e.printStackTrace();
        System.out.println("❌ Logout failed");
    }

    
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        // Browser will remain open
    }

}
