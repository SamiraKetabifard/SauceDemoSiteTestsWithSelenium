package com.example.Pages;
import com.example.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.Arrays;
import java.util.List;

public class LoginPage extends BasePage<LoginPage> {

    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By loginCredentials = By.cssSelector("[data-test='login-credentials']");
    private By passwordCredentials = By.cssSelector("[data-test='login-password']");
    private By errorMessage = By.cssSelector("[data-test='error']");

    private By inventoryContainer = By.id("inventory_container");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        driver.get("https://www.saucedemo.com/");
        waitUntilElementVisibility(usernameField);
        return this;
    }
    public LoginPage refresh() {
        super.refresh();
        return this;
    }
    public List<String> getAcceptedUsernames() {
        var usernames = getText(loginCredentials);
        var usernamesSplit = usernames.split("\n");
        return Arrays.stream(usernamesSplit)
                .toList()
                .subList(1, usernamesSplit.length);
    }
    public String getCorrectUsername(){
        var usernames = getText(loginCredentials);
        var usernamesSplit = usernames.split("\n");
        return usernamesSplit[1];
    }
    /* Split and last element access is because of dom structure.
     * First element is `Password for all users:` text */
    public String getCorrectPassword() {
        var password = getText(passwordCredentials);
        String[] passwordSplit = password.split("\n");
        return passwordSplit[passwordSplit.length - 1];
    }

    public LoginPage writeUsername(String username) {
        sendKeys(usernameField, username);
        return this;
    }

    public LoginPage writePassword(String password) {
        sendKeys(passwordField, password);
        return this;
    }

    public HomePage loginHappyPath() {
        writeUsername(getCorrectUsername());
        writePassword(getCorrectPassword());
        return clickLoginButtonExpectingSuccess();
    }

    public HomePage clickLoginButtonExpectingSuccess() {
        click(loginButton);
        waitUntilElementPresence(inventoryContainer);
        return new HomePage(driver);
    }
    public LoginPage clickLoginButtonRaw() {
        click(loginButton);
        return this;
    }
    public String getErrorMessage() {
        return getText(errorMessage).toLowerCase();
    }

    public boolean isUserNameFieldVisible() {
        return isVisible(usernameField);
    }

    public boolean isPasswordFieldVisible() {
        return isVisible(passwordField);
    }

    public boolean isLoginButtonVisible() {
        return isVisible(loginButton);
    }
}

