package SmokeTests;


import Base.BaseTest;
import com.example.Pages.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckLoginVisibility extends BaseTest {

    @Test(groups = "smoke")
    @Story("Login functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that username field is visible.")
    public void usernameFieldExists() {
        var loginPage = new LoginPage(driver);

        // Open login page and check if username field is visible
        var result = loginPage.open()
                .isUserNameFieldVisible();

        // Verify username field is displayed
        Assert.assertTrue(result, "Username field should be visible.");
    }

    @Test(groups = "smoke")
    @Story("Login functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that password field is visible.")
    public void passwordFieldExists() {
        var loginPage = new LoginPage(driver);

        // Open login page and check if password field is visible
        var result = loginPage.open()
                .isPasswordFieldVisible();

        // Verify password field is displayed
        Assert.assertTrue(result, "Password field should be visible.");
    }

    @Test(groups = "smoke")
    @Story("Login functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that login button is visible.")
    public void loginButtonExists() {
        var loginPage = new LoginPage(driver);

        // Open login page and check if login button is visible
        var result = loginPage.open()
                .isLoginButtonVisible();

        // Verify login button is displayed
        Assert.assertTrue(result, "Login button should be visible.");
    }

    @Test(groups = "smoke")
    @Story("Login functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Website should provide at least 1 correct username.")
    public void getCorrectUsernames() {
        var loginPage = new LoginPage(driver);

        // Open login page and get list of valid usernames from page
        var result = loginPage.open()
                .getAcceptedUsernames();

        // Verify at least one valid username is available
        Assert.assertFalse(result.isEmpty(), "Accepted usernames should not be empty.");
    }

    @Test(groups = "smoke")
    @Story("Login functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Website should provide at least 1 correct password.")
    public void getCorrectPasswords() {
        var loginPage = new LoginPage(driver);

        // Open login page and get valid password from page
        var result = loginPage.open()
                .getCorrectPassword();

        // Verify valid password is available
        Assert.assertFalse(result.isEmpty(), "Accepted passwords should not be empty.");
    }
}
