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
    //WebElement el = driver.findElement(locator);
    //Find the element, wait until it becomes visible, and return it
    protected WebElement findElementVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    //Wait until the element becomes visible without returning the element
    protected T waitUntilElementVisibility(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return (T) this;
    }
    //WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("user-name")));
    //driver.findElement(locator)+wait
    //Find the element and wait until it exists in the page
    protected WebElement findElementPresence(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    //Wait until the element exists in the page without returning the element
    protected T waitUntilElementPresence(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return (T) this;
    }
    //WebElement element = driver.findElement(By.id("user-name"));
    //element.clear();
    protected T clearTextField(WebElement element) {
        String value = element.getAttribute("value");
        if (value != null && !value.isEmpty())
            element.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        return (T) this;
    }
    //WebElement element = driver.findElement(By.id("user-name"));
    //element.clear();
    //element.sendKeys("standard_user");
    protected T sendKeys(By locator, String text) {
        var element = findElementVisibility(locator);
        clearTextField(element);
        element.sendKeys(text);
        wait.until(ExpectedConditions.attributeToBeNotEmpty(element, "value"));
        return (T) this;
    }
    //Find the element by its locator, then scroll to it smoothly
    protected T scrollToElementSmooth(By locator) {
        var element = findElementPresence(locator);
        ((JavascriptExecutor) driver).executeScript
                ("arguments[0].scrollIntoView({behavior: 'smooth'});", element);
        return (T) this;
    }
    //Scroll smoothly to an element that is already found
    protected T scrollToElementSmooth(WebElement element) {
        ((JavascriptExecutor) driver).executeScript
                ("arguments[0].scrollIntoView({behavior: 'smooth'});", element);
        return (T) this;
    }
    //Click an element using its locator (address)
    protected T click(By locator) {
        scrollToElementSmooth(locator);
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        return (T) this;
    }
    //Click an element that is already found as a WebElement
    protected T click(WebElement element) {
        scrollToElementSmooth(element);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        return (T) this;
    }
    protected T refresh() {
        driver.navigate().refresh();
        return (T) this;
    }
    /*protected String getText(By locator) {
        WebElement element = driver.findElement(locator);
        return element.getText().trim();
    */
    protected String getText(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator)).getText().trim();
    }
    //protected boolean isVisible(By locator) {
    //    WebElement element = driver.findElement(locator);
    //    return element.isDisplayed();}
    protected boolean isVisible(By locator) {
        return findElementVisibility(locator).isDisplayed();
    }
    protected List<WebElement> findElementsPresence(By locator) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    protected List<WebElement> findElementsVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
    protected String getTextInside(WebElement parent, By childLocator) {
        return findInside(parent, childLocator).getText().trim();
    }

    protected WebElement findInside(WebElement parent, By childLocator) {
        return wait.until(driver -> parent.findElement(childLocator));
    }
    protected boolean existsInside(WebElement parent, By childLocator) {
        try {
            parent.findElement(childLocator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    protected boolean isEmpty(By locator) {
        WebElement el = findElementVisibility(locator);
        String value = el.getTagName().equalsIgnoreCase("input") || el.getTagName().equalsIgnoreCase("textarea")
                ? el.getAttribute("value")
                : el.getText();
        return value == null || value.isEmpty();
    }


}
