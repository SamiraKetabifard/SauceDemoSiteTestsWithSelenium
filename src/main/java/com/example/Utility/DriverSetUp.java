package com.example.Utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverSetUp {

    private static WebDriver driver;
    private static String currentBrowser;

    private DriverSetUp() {
    }

    public static WebDriver getDriver(String browser) {

        if (browser == null || browser.isEmpty()) {
            throw new IllegalArgumentException(
                    "Browser name cannot be null or empty"
            );
        }

        browser = browser.toLowerCase().trim();

        if (driver != null) {

            if (!browser.equals(currentBrowser)) {
                throw new IllegalStateException(
                        "Driver already initialized for '" + currentBrowser +
                                "', requested '" + browser +
                                "'. Call DriverManager.quit() first."
                );
            }

            return driver;
        }

        switch (browser) {

            case "chrome" -> {

                System.setProperty(
                        "webdriver.chrome.driver",
                        "C:\\Users\\parsian\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe"
                );

                var options = getChromeOptions();

                driver = new ChromeDriver(options);
                currentBrowser = "chrome";
            }

            case "firefox" -> {

                var options = getFirefoxOptions();

                driver = new FirefoxDriver(options);
                currentBrowser = "firefox";
            }

            default -> throw new IllegalStateException(
                    "Unexpected value: " + browser
            );
        }

        driver.manage().window().maximize();

        return driver;
    }

    private static FirefoxOptions getFirefoxOptions() {

        var options = new FirefoxOptions();

        String headlessEnv =
                System.getProperty("headless", System.getenv("HEADLESS"));

        boolean isCI =
                System.getenv("CI") != null ||
                        "true".equalsIgnoreCase(headlessEnv);

        if (isCI) {
            options.addArguments("--headless");
        }

        options.addArguments("--private",
                "--disable-notifications");

        return options;
    }

    private static ChromeOptions getChromeOptions() {

        var options = new ChromeOptions();

        String headlessEnv =
                System.getProperty("headless", System.getenv("HEADLESS"));

        boolean isCI =
                System.getenv("CI") != null ||
                        "true".equalsIgnoreCase(headlessEnv);

        if (isCI) {
            options.addArguments("--headless=new");
        }

        options.addArguments(
                "--incognito",
                "--disable-notifications",
                "--disable-popup-blocking",
                "--disable-gpu");

        return options;
    }
    public static void quit() {

        if (driver != null) {

            try {
                driver.quit();

            } finally {
                driver = null;
                currentBrowser = null;
            }
        }
    }
}
