package RegressionTest;

import Base.BaseTest;
import com.example.Pages.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddToCartTest extends BaseTest {

    @Test(groups = "regression")
    @Story("Add to cart functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Description("User should be able to add item to cart.")
    public void loginAndClickAddToCartForRandomItem() {
        var loginPage = new LoginPage(driver);
        var homePage = loginPage.open().loginHappyPath();
        // Verify user is on home page
        Assert.assertTrue(homePage.atHomePage(), "Home page should be opened.");
        // Add random product to cart
        var product = homePage.getRandomProduct();
        homePage.clickAddToCartButton(product);
        // Verify product is added (remove button should appear)
        Assert.assertTrue(homePage.isProductHasRemoveFromCartButton(product),
                String.format("Product %s is not added to the cart.", product));
    }
    @Test(groups = "regression")
    @Story("Add to cart functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Add to cart button works for all products.")
    public void clickingAddToCartButtonWorks() {
        var loginPage = new LoginPage(driver);
        var homePage = loginPage.open().loginHappyPath();
        // Add each product to cart and verify button changes
        homePage.getProducts().forEach(product -> {
            homePage.clickAddToCartButton(product);

            // Verify remove button appears after adding to cart
            Assert.assertTrue(homePage.isProductHasRemoveFromCartButton(product),
                    "Product should be added to cart after clicking add to cart button.");
        });
    }
}
