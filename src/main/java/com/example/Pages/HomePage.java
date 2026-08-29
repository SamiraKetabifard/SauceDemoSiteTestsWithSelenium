package com.example.Pages;

import com.example.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
    private By cartIcon = By.cssSelector("[data-test='shopping-cart-link']");


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean atHomePage() {
        return isVisible(inventoryContainer);

    }
}