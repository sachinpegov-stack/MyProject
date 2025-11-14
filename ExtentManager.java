package com.example.test.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports createInstance(String reportName) {
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportName);
        reporter.config().setTheme(Theme.STANDARD);
        reporter.config().setDocumentTitle("M-Pension Automation Report");
        reporter.config().setReportName("Test Execution Summary");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Application", "MJP Pension Portal");
        extent.setSystemInfo("Tester", "Sachin Pawar");
        extent.setSystemInfo("Environment", "Staging");

        return extent;
    }
}
