package SmokeTests;


import Base.BaseTest;
import com.example.Pages.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckHomePageVisibility extends BaseTest {

    @Test(groups = "smoke")
    @Story("Home functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Product list should have at least one product.")
    public void thereMustBeAtLeastOneProduct() {
        var loginPage = new LoginPage(driver);

        // Login and get list of products from home page
        var result = loginPage.open()
                .loginHappyPath()
                .getProducts();

        // Verify at least one product is displayed
        Assert.assertFalse(result.isEmpty(), "There should be at least one product");
    }

    @Test(groups = "smoke")
    @Story("Home functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Product has an image.")
    public void productShouldHaveImage() {
        var loginPage = new LoginPage(driver);
        var homePage = loginPage.open()
                .loginHappyPath();

        // Get first product and check if it has an image
        var firstProduct = homePage.getFirstProduct();
        var result = homePage.productHasImage(firstProduct);

        // Verify product has an image
        Assert.assertTrue(result, firstProduct + " should have an image.");
    }

    @Test(groups = "smoke")
    @Story("Home functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("First product should have a description.")
    public void firstProductShouldHaveDescription() {
        var loginPage = new LoginPage(driver);
        var homePage = loginPage.open()
                .loginHappyPath();

        // Get first product and check if it has a description
        var firstProduct = homePage.getFirstProduct();
        var result = homePage.productHasDescription(firstProduct);

        // Verify product has a description
        Assert.assertTrue(result, firstProduct + " should have a description.");
    }

    @Test(groups = "smoke")
    @Story("Home functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("First product description is longer than 5 character.")
    public void firstProductDescriptionShouldBeLongerThan5Characters() {
        var loginPage = new LoginPage(driver);
        var homePage = loginPage.open()
                .loginHappyPath();

        // Get first product description text
        var firstProduct = homePage.getFirstProduct();
        var result = homePage.getProductDescription(firstProduct);

        // Verify description has meaningful length (more than 5 characters)
        Assert.assertTrue(result.length() > 5, firstProduct + " should have at least 5 character long description.");
    }

    @Test(groups = "smoke")
    @Story("Home functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Every product should have a description.")
    public void everyProductShouldHaveDescription() {
        var loginPage = new LoginPage(driver);
        var homePage = loginPage.open()
                .loginHappyPath();

        // Verify each product in the list has a description
        homePage.getProducts().forEach(product -> {
            Assert.assertFalse(homePage.getProductDescription(product).isEmpty(),
                    product + " should have a description.");
        });
    }

    @Test(groups = "smoke")
    @Story("Home functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Every product has add to cart button.")
    public void addToCartButtonExistsForAllProducts() {
        var loginPage = new LoginPage(driver);
        var homePage = loginPage.open()
                .loginHappyPath();

        // Verify each product has an add to cart button
        homePage.getProducts().forEach(product -> {
            Assert.assertTrue(homePage.isProductHasAddToCartButton(product),
                    product + " should have an add to cart button.");
        });
    }
}
