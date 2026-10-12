package RegressionTest;

import Base.BaseTest;
import com.example.Pages.LoginPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SortNamesTest extends BaseTest {

    @Test(groups = "regression")
    @Story("Product sorting functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Sorting products by name A-Z should display products in ascending alphabetical order.")
    public void sortByNameAZ() {
        var loginPage = new LoginPage(driver);
        var homePage = loginPage.open().loginHappyPath();
        // Get initial product titles
        var productsBefore = homePage.getProducts();
        var titlesBefore = homePage.getProductTitles(productsBefore);
        // Sort products by name A-Z
        var productsAfter = homePage.clickProductSortAZ().getProducts();
        var titlesAfter = homePage.getProductTitles(productsAfter);
        // Verify products are in ascending alphabetical order
        for (int i = 0; i < titlesAfter.size() - 1; i++) {
            Assert.assertTrue(titlesAfter.get(i).compareToIgnoreCase(titlesAfter.get(i + 1)) <= 0,
                    String.format("Products should be in ascending order. %s should come before %s",
                            titlesAfter.get(i), titlesAfter.get(i + 1)));
        }
    }

    @Test(groups = "regression")
    @Story("Product sorting functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Sorting products by name Z-A should display products in descending alphabetical order.")
    public void sortByNameZA() {
        var loginPage = new LoginPage(driver);
        var homePage = loginPage.open().loginHappyPath();
        // Get initial product titles
        var productsBefore = homePage.getProducts();
        var titlesBefore = homePage.getProductTitles(productsBefore);
        // Sort products by name Z-A
        var productsAfter = homePage.clickProductSortZA().getProducts();
        var titlesAfter = homePage.getProductTitles(productsAfter);
        // Verify products are in descending alphabetical order
        for (int i = 0; i < titlesAfter.size() - 1; i++) {
            Assert.assertTrue(titlesAfter.get(i).compareToIgnoreCase(titlesAfter.get(i + 1)) >= 0,
                    String.format("Products should be in descending order. %s should come after %s",
                            titlesAfter.get(i), titlesAfter.get(i + 1)));
        }
    }
    @Test(groups = "regression")
    @Story("Product sorting functionality")
    @Severity(SeverityLevel.NORMAL)
    @Description("Sorting by name A-Z to Z-A should change the product order.")
    public void nameSortChangesProductOrder() {
        var loginPage = new LoginPage(driver);
        var homePage = loginPage.open().loginHappyPath();
        // Get initial product order
        var productsInitial = homePage.getProducts();
        var titlesInitial = homePage.getProductTitles(productsInitial);
        // Sort products by name Z-A
        var productsSorted = homePage.clickProductSortZA().getProducts();
        var titlesSorted = homePage.getProductTitles(productsSorted);
        // Verify product order changed after sorting
        Assert.assertNotEquals(titlesInitial, titlesSorted,
                "Product order should change after sorting.");
    }
}
