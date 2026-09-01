package com.example.Pages;

import com.example.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Random;

public class HomePage extends BasePage<HomePage> {

    private By inventoryContainer = By.className("inventory_list");
    private By inventoryProducts = By.cssSelector(".inventory_list .inventory_item");
    private By addToCartButtonGeneric = By.cssSelector("[data-test*='add-to-cart']");
    private By removeFromCartButtonGeneric = By.cssSelector("[data-test*='remove']");
    private By productPrice = By.cssSelector("[data-test='inventory-item-price']");
    private By productTitle = By.cssSelector("[data-test='inventory-item-name']");
    private By productSortContainer = By.cssSelector("[data-test='product-sort-container']");
    private By productSortAZ = By.cssSelector("[data-test='product-sort-container'] option[value='az']");
    private By productSortZA = By.cssSelector("[data-test='product-sort-container'] option[value='za']");
    private By productSortPriceLowHigh = By.cssSelector("[data-test='product-sort-container'] option[value='lohi']");
    private By productSortPriceHighLow = By.cssSelector("[data-test='product-sort-container'] option[value='hilo']");
    private By cartBadge = By.cssSelector("[data-test='shopping-cart-badge']");

    public HomePage(WebDriver driver) {
        super(driver);
    }
    public boolean atHomePage() {
        return isVisible(inventoryContainer);
    }
    public List<WebElement> getProducts() {
        return findElementsVisibility(inventoryProducts);
    }
    public WebElement getFirstProduct() {
        return findElementsPresence(inventoryProducts).getFirst();
    }
    public WebElement getSecondProduct() {
        return findElementsPresence(inventoryProducts).get(1);
    }
    public WebElement getRandomProduct() {
        var products = getProducts();
        Random random = new Random();
        return products.get(random.nextInt(products.size()));
    }
    public boolean productHasImage(WebElement product) {
        return existsInside(product, By.tagName("img"));
    }
    public boolean productHasDescription(WebElement product) {
        return existsInside(product, By.cssSelector("[data-test='inventory-item-desc']"));
    }
    public String getProductDescription(WebElement product) {
        return getTextInside(product, By.cssSelector("[data-test='inventory-item-desc']"));
    }
    public boolean isProductHasAddToCartButton(WebElement element) {
        return existsInside(element, addToCartButtonGeneric);
    }
    public boolean isProductHasRemoveFromCartButton(WebElement element) {
        return existsInside(element, removeFromCartButtonGeneric);
    }
    public HomePage clickAddToCartButton(WebElement element) {
        click(findInside(element, addToCartButtonGeneric));
        return this;
    }
    public String getProductPrice(WebElement product) {
        return getTextInside(product, productPrice);
    }
    public String getProductTitle(WebElement product) {
        var title = getTextInside(product, productTitle);
        if (title.contains("Sauce Labs")) {
            title = title.replace("Sauce Labs", "");
            return title;
        }
        return title;
    }
    public List<String> getProductTitles(List<WebElement> elements) {
        return elements.stream().map(this::getProductTitle).toList();
    }
    public HomePage clickProductSortContainer() {
        var container = findElementVisibility(productSortContainer);
        click(container);
        return this;
    }
    public HomePage clickProductSortAZ() {
        clickProductSortContainer();
        click(productSortAZ);
        return this;
    }
    public HomePage clickProductSortZA() {
        clickProductSortContainer();
        click(productSortZA);
        return this;
    }
    public HomePage clickProductSortPriceLowHigh() {
        clickProductSortContainer();
        click(productSortPriceLowHigh);
        return this;
    }
    public HomePage clickProductSortPriceHighLow() {
        clickProductSortContainer();
        click(productSortPriceHighLow);
        return this;
    }
    public List<String> getProductPrices(List<WebElement> products) {
        return products.stream().map(this::getProductPrice).toList();
    }
    public double parsePrice(String priceText) {
        return Double.parseDouble(priceText.replace("$", ""));
    }
    public String getCartBadgeCount() {
        // Wait for badge to be visible before getting text
        waitUntilElementVisibility(cartBadge);
        return getText(cartBadge);
    }
    public boolean hasCartBadge() {
        try {
            return findElementVisibility(cartBadge).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public HomePage clickRemoveFromCartButton(WebElement product) {
        click(findInside(product, removeFromCartButtonGeneric));
        return this;
    }
}
