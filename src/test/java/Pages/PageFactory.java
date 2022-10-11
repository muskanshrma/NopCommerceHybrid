package Pages;

import org.openqa.selenium.WebDriver;

public class PageFactory {
    WebDriver driver;
    private AddToCartAndCheckout checkoutPage;

    public PageFactory(WebDriver driver) {
        this.driver = driver;
    }

    public AddToCartAndCheckout getAddToCartAndCheckout() {
        if (checkoutPage == null) {
            checkoutPage = new AddToCartAndCheckout(driver);
        }
        return checkoutPage;
    }
}