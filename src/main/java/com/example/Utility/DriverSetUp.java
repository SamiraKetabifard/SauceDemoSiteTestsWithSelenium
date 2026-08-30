package com.example.Utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverSetUp {

    private static WebDriver driver;

    private DriverSetUp() {
    }

    public static WebDriver getDriver() {

        if (driver == null) {

            System.setProperty(
                    "webdriver.chrome.driver",
                    "C:\\Users\\parsian\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe"
            );

            ChromeOptions options = new ChromeOptions();

            options.addArguments(
                    "--incognito",
                    "--disable-notifications"
            );

            driver = new ChromeDriver(options);

            driver.manage().window().maximize();
        }

        return driver;
    }

    public static void quit() {

        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}