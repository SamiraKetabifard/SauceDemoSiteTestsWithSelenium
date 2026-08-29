package PositiveTests;

import Base.BaseTest;
import Utility.DataProviders;
import com.example.Pages.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPositiveTests extends BaseTest {

    @Test(groups = "positive")
    @Story("Login functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Correct password doesn't create error message.")
    public void correctPasswordDoesntCauseError() {
        var loginPage = new LoginPage(driver);

        // Open login page and get password
        loginPage.open();
        String password = loginPage.getCorrectPassword();

        // Enter correct password only (no username)
        var result = loginPage.writePassword(password)
                .clickLoginButtonRaw()
                .getErrorMessage();

        // Verify error message doesn't mention password (username error is expected)
        Assert.assertFalse(result.contains("password"), "Error message should not contain 'password'.");
    }
    @Test(dataProvider = "correctUsernames", dataProviderClass = DataProviders.class, groups = "positive")
    @Story("Login functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Correct username doesn't create error message.")
    public void correctUsernameDoesntCauseError(String username) {
        var loginPage = new LoginPage(driver);

        // Open login page and enter correct username only (no password)
        var result = loginPage.open()
                .writeUsername(username)
                .clickLoginButtonRaw()
                .getErrorMessage();

        // Verify error message doesn't mention username (password error is expected)
        Assert.assertFalse(result.contains("username"), "Error message should not contain 'username'.");
    }

    @Test(dataProvider = "correctCredentials", dataProviderClass = DataProviders.class ,groups = "positive")
    @Story("Login functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Correct username and password logins correctly.")
    public void correctPasswordAndCorrectUsernameLogins(String username, String password) {
        var loginPage = new LoginPage(driver);

        // Open login page and login with correct username and password
        var result = loginPage.open().writeUsername(username)
                .writePassword(password)
                .clickLoginButtonExpectingSuccess();

        // Verify user is redirected to home page
        Assert.assertTrue(result.atHomePage(), "Should be in home page.");
    }


    @Test(dataProvider = "correctCredentials", dataProviderClass = DataProviders.class ,groups = "positive")
    @Story("Login functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Correct username and password from data provider should work correctly.")
    public void correctUsernameAndPasswordFromDataProvider(String username, String password){
        var loginPage = new LoginPage(driver);

        // Login with credentials from data provider (tests all valid usernames)
        var result = loginPage.open()
                .writeUsername(username)
                .writePassword(password)
                .clickLoginButtonExpectingSuccess();

        // Verify user is redirected to home page for all valid credentials
        Assert.assertTrue(result.atHomePage(), "Should be in home page.");
    }

    @Test(dataProvider = "correctCredentials", dataProviderClass = DataProviders.class ,groups = "positive")
    @Story("Login functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("User should be able to login multiple times successfully.")
    public void multipleSuccessfulLogins(String username, String password) {
        var loginPage = new LoginPage(driver);

        // First login
        var homePage1 = loginPage.open()
                .writeUsername(username)
                .writePassword(password)
                .clickLoginButtonExpectingSuccess();
        Assert.assertTrue(homePage1.atHomePage(), "First login should be successful.");

        // Second login (after logout or new session)
        var homePage2 = loginPage.open()
                .writeUsername(username)
                .writePassword(password)
                .clickLoginButtonExpectingSuccess();
        Assert.assertTrue(homePage2.atHomePage(), "Second login should be successful.");
    }

    @Test(dataProvider = "correctCredentials", dataProviderClass = DataProviders.class ,groups = "positive")
    @Story("Login functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Login should work after refreshing the login page.")
    public void loginAfterPageRefresh(String username, String password) {
        var loginPage = new LoginPage(driver);

        // Open login page and refresh
        loginPage.open();
        loginPage.refresh();

        // Login after refresh
        var result = loginPage.writeUsername(username)
                .writePassword(password)
                .clickLoginButtonExpectingSuccess();

        // Verify login works after refresh
        Assert.assertTrue(result.atHomePage(), "Login should work after page refresh.");
    }

    @Test(groups = "positive")
    @Story("Login functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("User should stay logged in after navigating to home page.")
    public void userStaysLoggedInOnHomePage() {
        var loginPage = new LoginPage(driver);

        // Login and navigate to home page
        var homePage = loginPage.open()
                .loginHappyPath();

        // Verify user is still on home page
        Assert.assertTrue(homePage.atHomePage(), "User should stay on home page after login.");
    }

}
