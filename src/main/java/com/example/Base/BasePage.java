package com.example.Base;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public abstract class BasePage <T extends BasePage <T>>{

    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final Actions action;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        this.action = new Actions(driver);
    }
    protected WebElement findElementVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    protected WebElement findElementPresence(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    protected T waitUntilElementVisibility(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return (T) this;
    }
    protected T waitUntilElementPresence(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return (T) this;
    }
    protected T clearTextField(WebElement element) {
        String value = element.getAttribute("value");
        if (value != null && !value.isEmpty())
            element.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        return (T) this;
    }
    protected T sendKeys(By locator, String text) {
        var element = findElementVisibility(locator);
        clearTextField(element);
        element.sendKeys(text);
        wait.until(ExpectedConditions.attributeToBeNotEmpty(element, "value"));
        return (T) this;
    }
    protected T scrollToElementSmooth(By locator) {
        var element = findElementPresence(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth'});", element);
        return (T) this;
    }
    protected T scrollToElementSmooth(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth'});", element);
        return (T) this;
    }
    protected T click(By locator) {
        scrollToElementSmooth(locator);
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        return (T) this;
    }
    protected T click(WebElement element) {
        scrollToElementSmooth(element);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        return (T) this;
    }
    /*protected T simpleClick(By locator) {
        var element = findElementVisibility(locator);
        element.click();
        return (T) this;
    }*/
    protected T refresh() {
        driver.navigate().refresh();
        return (T) this;
    }
    protected String getText(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator)).getText().trim();
    }
    protected boolean isVisible(By locator) {
        return findElementVisibility(locator).isDisplayed();
    }






}
