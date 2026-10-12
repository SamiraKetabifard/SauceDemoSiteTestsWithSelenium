package RegressionTest;

import Base.BaseTest;
import com.example.Pages.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RemoveFromCartTest extends BaseTest {

    @Test(groups = "regression")
    @Story("Remove from cart functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Description("User should be able to remove item from cart on home page.")
    public void removeItemFromCartOnHomePage() {
        var loginPage = new LoginPage(driver);
        var homePage = loginPage.open().loginHappyPath();
        // Add product to cart
        var product = homePage.getFirstProduct();
        homePage.clickAddToCartButton(product);
        // Verify added
        Assert.assertTrue(homePage.isProductHasRemoveFromCartButton(product),
                "Product should be added to cart.");
        // Remove from cart
        homePage.clickRemoveFromCartButton(product);
        // Verify removed - Add to cart button should be back
        Assert.assertTrue(homePage.isProductHasAddToCartButton(product),
                "Add to cart button should appear after removing item.");
    }

    @Test(groups = "regression")
    @Story("Remove from cart functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Cart badge should disappear when all items are removed.")
    public void cartBadgeDisappearsWhenAllItemsRemoved() {
        var loginPage = new LoginPage(driver);
        var homePage = loginPage.open().loginHappyPath();
        // Add product to cart
        var product = homePage.getFirstProduct();
        homePage.clickAddToCartButton(product);
        // Verify badge appears
        Assert.assertTrue(homePage.hasCartBadge(), "Cart badge should be visible.");
        // Remove from cart
        homePage.clickRemoveFromCartButton(product);
        // Verify badge disappears
        Assert.assertFalse(homePage.hasCartBadge(), "Cart badge should disappear when cart is empty.");
    }

    @Test(groups = "regression")
    @Story("Remove from cart functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Cart badge count should decrease when item is removed.")
    public void cartBadgeCountDecreasesOnRemove() {
        var loginPage = new LoginPage(driver);
        var homePage = loginPage.open().loginHappyPath();
        // Add two products to cart
        var product1 = homePage.getFirstProduct();
        homePage.clickAddToCartButton(product1);

        var product2 = homePage.getSecondProduct();
        homePage.clickAddToCartButton(product2);

        // Verify badge shows 2 items
        Assert.assertEquals(homePage.getCartBadgeCount(), "2", "Cart should show 2 items.");

        // Remove one item from cart
        homePage.clickRemoveFromCartButton(product1);

        // Verify badge count decreases to 1
        Assert.assertEquals(homePage.getCartBadgeCount(), "1", "Cart should show 1 item after removing one.");
    }

    @Test(groups = "regression")
    @Story("Remove from cart functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("User should be able to add and remove multiple items.")
    public void addAndRemoveMultipleItems() {
        var loginPage = new LoginPage(driver);
        var homePage = loginPage.open().loginHappyPath();

        var products = homePage.getProducts();
        int itemsToTest = Math.min(3, products.size());
        // Add multiple items to cart (up to 3 items)
        for (int i = 0; i < itemsToTest; i++) {
            homePage.clickAddToCartButton(products.get(i));
        }
        // Verify badge shows correct count for all items
        Assert.assertEquals(homePage.getCartBadgeCount(), String.valueOf(itemsToTest),
                String.format("Cart should show %d items.", itemsToTest));
        // Remove all items from cart
        for (int i = 0; i < itemsToTest; i++) {
            homePage.clickRemoveFromCartButton(products.get(i));
        }
        // Verify cart badge disappears when cart is empty
        Assert.assertFalse(homePage.hasCartBadge(), "Cart badge should not be visible when cart is empty.");
    }
}
