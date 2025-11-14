package com.example.test.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

    protected static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    protected static ThreadLocal<StringBuilder> logBuffer = ThreadLocal.withInitial(StringBuilder::new);

    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    public static void log(String message) {
        logBuffer.get().append(message).append("\n");
    }

    public static String getLogBuffer() {
        return logBuffer.get().toString();
    }

    public static void clearLogBuffer() {
        logBuffer.get().setLength(0);
    }


    // --------------------------
    // Launch Browser
    // --------------------------
    public void launchBrowser(String browser) {

        WebDriver driver = null;
        log("Launching Browser: " + browser);

        switch (browser.toLowerCase()) {

        case "chrome":
            try {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                System.out.println("✅ Chrome launched via WebDriverManager");
            } catch (Exception e) {
                System.out.println("❌ WDM failed for Chrome, using local driver...");
                System.setProperty("webdriver.chrome.driver", "D:\\WebDrivers\\chromedriver.exe");
                driver = new ChromeDriver();
            }
            break;

            case "edge":
                // ⚠ FIX: DO NOT USE WebDriverManager FOR EDGE
                // Download msedgedriver and place in C:\drivers\
                System.setProperty("webdriver.edge.driver", "D:\\WebDrivers\\msedgedriver.exe");
                driver = new EdgeDriver();
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            default:
                throw new RuntimeException("Invalid Browser: " + browser);
        }

        tlDriver.set(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        log("Browser Launched Successfully.");
    }

    // --------------------------
    // DO NOT QUIT BROWSER
    // --------------------------
    public void quitBrowser() {
        log("Browser will remain open. Not quitting.");
    }
}
