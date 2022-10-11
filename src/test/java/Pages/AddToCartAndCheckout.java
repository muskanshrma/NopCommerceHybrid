package Pages;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class AddToCartAndCheckout {
    WebDriver driver;
    WebDriverWait wait;
    String productName = "Apple MacBook Pro 13-inch";
    String frstName = "FirstName";
    String lName = "LastName";
    String email = "Email";
    String pass = "Password";
    String confirmpass = "ConfirmPassword";

    By computers = By.xpath("(//a[contains(text(), 'Computers')])[1]");
    By notebooks = By.linkText("Notebooks");
    By totalProducts = By.xpath("//h2//a");
    By productPrice = By.xpath("//span[contains(@class,'price actual-price')]");
    By sort = By.xpath("//select[@name='products-orderby']");
    By sortCategory = By.xpath("//option[@value='10']");
    By displayFilter = By.xpath("//select[@name='products-pagesize']");
    By displayFilterValue = By.xpath("//option[@value='9']");
    By product = By.linkText(String.format("%s", productName));
    By addToCart = By.xpath("(//button[contains(@class,'add-to-cart-button')])[1]");
    By shopCart = By.xpath("//a[contains(text(), 'shopping cart')]");
    By orderQuantity = By.xpath("//input[@class='qty-input']");
    By terms = By.xpath("//input[@name='termsofservice']");
    By checkout = By.xpath("//button[@name='checkout']");
    By registerButton = By.xpath("//button[contains(@class,'register-button')]");
    By firstName = By.xpath(String.format("//input[@name='%s']", frstName));
    By lastName = By.xpath(String.format("//input[@name='%s']", lName));
    By emailid = By.xpath(String.format("//input[@name='%s']", email));
    By password = By.xpath(String.format("//input[@name='%s']", pass));
    By confirmPassword = By.xpath(String.format("//input[@name='%s']", confirmpass));
    By register = By.xpath("//button[@name='register-button']");
    By continueButton = By.xpath("//a[contains(@class,'register-continue-button')]");
    By country = By.xpath("//select[@name='BillingNewAddress.CountryId']");
    By stateProvince = By.xpath("//select[@name='BillingNewAddress.StateProvinceId']");
    By UsCountry = By.xpath("//option[@value='1'][contains(text(),'United States')]");
    By state = By.xpath("//option[@value='52'][contains(text(),'Alaska')]");
    By cityName = By.xpath("//input[@name='BillingNewAddress.City']");
    By address = By.xpath("//input[@name='BillingNewAddress.Address1']");
    By zip = By.xpath("//input[@name='BillingNewAddress.ZipPostalCode']");
    By phone = By.xpath("//input[@name='BillingNewAddress.PhoneNumber']");
    By cont = By.xpath("(//button[contains(text(), 'Continue')])[1]");
    By continueButton1 = By.xpath("//button[contains(@onclick,'Billing.save()')]");
    By continueButton2 = By.xpath("//button[contains(@class,'shipping-method-next-step-button')]");
    By continueButton3 = By.xpath("//button[contains(@class,'shipping-method-next-step-button')]");
    By continueButton4 = By.xpath("//button[contains(@class,'payment-method-next-step-button')]");
    By continueButton5 = By.xpath("//button[contains(@class,'payment-info-next-step-button')]");
    By confirm = By.xpath("//button[contains(@class,'confirm-order-next-step-button')]");
    By orderPlacedVerification = By.xpath("//h1[contains(text(),'Thank you')]");

    public AddToCartAndCheckout(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void addToCart() throws InterruptedException {

        driver.findElement(computers).click();
        driver.findElement(notebooks).click();
        driver.findElement(sort).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(sortCategory));
        driver.findElement(sortCategory).click();
        Thread.sleep(3000); //Hard wait because pause is required
        wait.until(ExpectedConditions.visibilityOfElementLocated(displayFilter));
        driver.findElement(displayFilter).click();
        wait.until(ExpectedConditions.elementToBeClickable(displayFilterValue));
        driver.findElement(displayFilterValue).click();
        Thread.sleep(3000); //Hard wait because pause is required

        List<WebElement> allProducts = driver.findElements(totalProducts);
        int allProductsCount = allProducts.size();
        System.out.println("Total products: " + allProductsCount);
        for (WebElement allElements : allProducts) {
            String productName = allElements.getText();
            System.out.println(productName);
        }
        List<WebElement> allProductsPrice = driver.findElements(productPrice);
        for (WebElement allElementPrice : allProductsPrice) {
            String productPrice = allElementPrice.getText();
            System.out.println(productPrice);
        }
        wait.until(ExpectedConditions.elementToBeClickable(product));
        driver.findElement(product).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(addToCart));
        driver.findElement(addToCart).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(shopCart));
        boolean isResultDisplayed = driver.findElement(shopCart).isDisplayed();
        org.testng.Assert.assertTrue(isResultDisplayed, "Order not added to cart");
        driver.findElement(shopCart).click();
        driver.findElement(orderQuantity).clear();
        driver.findElement(orderQuantity).sendKeys("4");
        wait.until(ExpectedConditions.elementToBeClickable(terms));
        driver.findElement(terms).click();
        driver.findElement(checkout).click();
    }
    public void registerUser()throws IOException {

        String path = System.getProperty("user.dir") + "//src//test//java//TestData//DemoNopRegister.xlsx";
        FileInputStream prop1 = null;
        try {
            prop1 = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        XSSFWorkbook wb = new XSSFWorkbook(prop1);
        XSSFSheet sheet = wb.getSheet("Sheet1");
        String fName = sheet.getRow(1).getCell(0).getStringCellValue();
        String lName = sheet.getRow(1).getCell(1).getStringCellValue();
        String email = sheet.getRow(1).getCell(2).getStringCellValue();
        String pass = sheet.getRow(1).getCell(3).getStringCellValue();
        XSSFSheet sheet2 = wb.getSheet("Sheet2");
        String city = sheet2.getRow(1).getCell(0).getStringCellValue();
        String add = sheet2.getRow(1).getCell(1).getStringCellValue();
        String zipcode = sheet2.getRow(1).getCell(2).getStringCellValue();
        String num = sheet2.getRow(1).getCell(3).getStringCellValue();

        driver.findElement(registerButton).click();
        driver.findElement(firstName).sendKeys(fName);
        driver.findElement(lastName).sendKeys(lName);
        driver.findElement(emailid).sendKeys(email);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(confirmPassword).sendKeys(pass);
        driver.findElement(register).click();
        driver.findElement(continueButton).click();
        driver.findElement(terms).click();
        driver.findElement(checkout).click();
        driver.findElement(country).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(UsCountry));
        driver.findElement(UsCountry).click();
        driver.findElement(stateProvince).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(state));
        driver.findElement(state).click();
        driver.findElement(cityName).sendKeys(city);
        driver.findElement(address).sendKeys(add);
        driver.findElement(zip).sendKeys(zipcode);
        driver.findElement(phone).sendKeys(num);
        driver.findElement(cont).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(continueButton1));
        driver.findElement(continueButton1).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(continueButton2));
        driver.findElement(continueButton2).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(continueButton3));
        driver.findElement(continueButton3).click();
        wait.until(ExpectedConditions.elementToBeClickable(continueButton4));
        driver.findElement(continueButton4).click();
        wait.until(ExpectedConditions.elementToBeClickable(continueButton5));
        driver.findElement(continueButton5).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirm));
        driver.findElement(confirm).click();
    }

    public void verifyOrderPlaced() throws IOException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderPlacedVerification));
        String actual = driver.findElement(orderPlacedVerification).getText();
        Assert.assertEquals(actual, "Thank you", "Expected result does not match with actual result");
    }
}