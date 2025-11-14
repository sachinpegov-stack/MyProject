package com.example.test.listeners;

import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.example.test.base.BaseClass;

public class AllureListener implements ITestListener {

    private WebDriver getDriver() {
        return BaseClass.getDriver();
    }

    @Override
    public void onTestStart(ITestResult result) {
        AllureManager.log("🚀 Test Started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        AllureManager.log("✅ Test Passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        AllureManager.log("❌ TEST FAILED → " + result.getThrowable().getMessage());
        
        attachScreenshot();
        attachPageSource();
        attachConsoleLogs(BaseClass.getLogBuffer());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        AllureManager.log("⚠️ Test Skipped: " + result.getMethod().getMethodName());
    }

    // ------- Allure Attachments -------

    @Attachment(value = "📸 Screenshot", type = "image/png")
    public byte[] attachScreenshot() {
        try {
            return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            return null;
        }
    }

    @Attachment(value = "📄 Page Source", type = "text/html")
    public byte[] attachPageSource() {
        try {
            return getDriver().getPageSource().getBytes();
        } catch (Exception e) {
            return null;
        }
    }

    @Attachment(value = "📝 Console Log", type = "text/plain")
    public String attachConsoleLogs(String message) {
        return message;
    }
}
