package RegressionTest;


import Base.BaseTest;
import com.example.Pages.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartFunctionalityTest extends BaseTest {

    @Test(groups = "regression")
    @Story("Shopping cart functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Cart badge should display correct count when items are added to cart.")
    public void cartBadgeShowsCorrectCount() {
        var loginPage = new LoginPage(driver);
        var homePage = loginPage.open().loginHappyPath();
        // Add 2 products to cart
        var product1 = homePage.getFirstProduct();
        homePage.clickAddToCartButton(product1);
        // Wait for badge to appear after first item
        Assert.assertTrue(homePage.hasCartBadge(), "Cart badge should appear after first item.");
        // Get second product (ensure it's different from first)
        var products = homePage.getProducts();
        var product2 = products.size() > 1 ? products.get(1) : products.get(0);
        // Make sure product2 is different from product1, or skip if same
        if (product1 != product2) {
            homePage.clickAddToCartButton(product2);
        }
        // Verify cart badge is visible and shows correct count
        Assert.assertTrue(homePage.hasCartBadge(), "Cart badge should be visible after adding items.");
        String badgeCount = homePage.getCartBadgeCount();
        // Verify badge shows correct count (1 if same product, 2 if different)
        int expectedCount = (product1 == product2) ? 1 : 2;
        Assert.assertEquals(badgeCount, String.valueOf(expectedCount),
                String.format("Cart badge should show %d item(s).", expectedCount));
    }

    @Test(groups = "regression")
    @Story("Shopping cart functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Cart badge count should increase when adding multiple items.")
    public void cartBadgeIncrementsCorrectly() {
        var loginPage = new LoginPage(driver);
        var homePage = loginPage.open().loginHappyPath();

        // Add first product and verify badge appears with count 1
        var product1 = homePage.getFirstProduct();
        homePage.clickAddToCartButton(product1);
        Assert.assertTrue(homePage.hasCartBadge(), "Cart badge should appear after first item.");
        Assert.assertEquals(homePage.getCartBadgeCount(), "1", "Cart should show 1 item.");

        // Add second product (ensure it's different from first)
        var products = homePage.getProducts();
        if (products.size() > 1) {
            var product2 = products.get(1);
            homePage.clickAddToCartButton(product2);
            Assert.assertEquals(homePage.getCartBadgeCount(), "2", "Cart should show 2 items.");

            // Add third product if available and verify badge count increases to 3
            if (products.size() >= 3) {
                var product3 = products.get(2);
                homePage.clickAddToCartButton(product3);
                Assert.assertEquals(homePage.getCartBadgeCount(), "3", "Cart should show 3 items.");
            }
        }
    }

    @Test(groups = "regression")
    @Story("Shopping cart functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Cart badge should not appear when cart is empty.")
    public void cartBadgeNotVisibleWhenEmpty() {
        var loginPage = new LoginPage(driver);
        var homePage = loginPage.open().loginHappyPath();
        // Verify cart badge is not visible initially
        Assert.assertFalse(homePage.hasCartBadge(), "Cart badge should not be visible when cart is empty.");
    }
    @Test(groups = "regression")
    @Story("Shopping cart functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Remove button should appear after adding item to cart.")
    public void removeButtonAppearsAfterAddToCart() {
        var loginPage = new LoginPage(driver);
        var homePage = loginPage.open().loginHappyPath();

        var product = homePage.getFirstProduct();
        // Verify remove button not visible initially
        Assert.assertFalse(homePage.isProductHasRemoveFromCartButton(product),
                "Remove button should not be visible before adding to cart.");
        // Add to cart
        homePage.clickAddToCartButton(product);
        // Verify remove button is now visible
        Assert.assertTrue(homePage.isProductHasRemoveFromCartButton(product),
                "Remove button should be visible after adding to cart.");
    }
}
