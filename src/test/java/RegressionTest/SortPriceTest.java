package RegressionTest;

import Base.BaseTest;
import com.example.Pages.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SortPriceTest extends BaseTest {

    @Test(groups = "regression")
    @Story("Product sorting functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Sorting products by price low to high should display products in ascending price order.")
    public void sortByPriceLowToHigh() {
        var loginPage = new LoginPage(driver);
        var homePage = loginPage.open().loginHappyPath();
        // Sort by price low to high
        var productsAfter = homePage.clickProductSortPriceLowHigh().getProducts();
        var pricesAfter = homePage.getProductPrices(productsAfter);
        // Verify prices are in ascending order
        for (int i = 0; i < pricesAfter.size() - 1; i++) {
            double currentPrice = homePage.parsePrice(pricesAfter.get(i));
            double nextPrice = homePage.parsePrice(pricesAfter.get(i + 1));
            // Each price should be less than or equal to the next price
            Assert.assertTrue(currentPrice <= nextPrice,
                    String.format("Prices should be in ascending order. %s should be <= %s",
                            pricesAfter.get(i), pricesAfter.get(i + 1)));
        }
    }
    @Test(groups = "regression")
    @Story("Product sorting functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Sorting products by price high to low should display products in descending price order.")
    public void sortByPriceHighToLow() {
        var loginPage = new LoginPage(driver);
        var homePage = loginPage.open().loginHappyPath();
        // Sort by price high to low
        var productsAfter = homePage.clickProductSortPriceHighLow().getProducts();
        var pricesAfter = homePage.getProductPrices(productsAfter);
        // Verify prices are in descending order
        for (int i = 0; i < pricesAfter.size() - 1; i++) {
            double currentPrice = homePage.parsePrice(pricesAfter.get(i));
            double nextPrice = homePage.parsePrice(pricesAfter.get(i + 1));
            // Each price should be greater than or equal to the next price
            Assert.assertTrue(currentPrice >= nextPrice,
                    String.format("Prices should be in descending order. %s should be >= %s",
                            pricesAfter.get(i), pricesAfter.get(i + 1)));
        }
    }
    @Test(groups = "regression")
    @Story("Product sorting functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Sorting by price low to high should change the product order.")
    public void priceSortChangesProductOrder() {
        var loginPage = new LoginPage(driver);
        var homePage = loginPage.open().loginHappyPath();
        // Get initial product prices
        var productsInitial = homePage.getProducts();
        var pricesInitial = homePage.getProductPrices(productsInitial);
        // Sort by price low to high
        var productsSorted = homePage.clickProductSortPriceLowHigh().getProducts();
        var pricesSorted = homePage.getProductPrices(productsSorted);
        // Verify price order changed after sorting (unless already sorted)
        Assert.assertNotEquals(pricesInitial, pricesSorted,
                "Product prices should change after sorting.");
    }
}
