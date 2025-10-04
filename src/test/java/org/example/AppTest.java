
package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class AppTest {

    WebDriver driver;

    // ----- Setup and Teardown -----
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // ----- Register Page Methods -----
    public void register(String firstName, String lastName, String email, String password) {
        driver.navigate().to("https://demowebshop.tricentis.com/register");
        driver.findElement(By.id("gender-male")).click();
        driver.findElement(By.id("FirstName")).sendKeys(firstName);
        driver.findElement(By.id("LastName")).sendKeys(lastName);
        driver.findElement(By.id("Email")).sendKeys(email);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
        driver.findElement(By.id("register-button")).click();
    }

    // ----- Login Page Methods -----
    public void login(String email, String password) {
        driver.navigate().to("https://demowebshop.tricentis.com/login");
        driver.findElement(By.id("Email")).sendKeys(email);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.id("RememberMe")).click();
        driver.findElement(By.xpath("//input[@value='Log in']")).click();
    }

    // ----- Product Page Methods -----
    public void searchProduct(String keyword) {
        driver.findElement(By.id("small-searchterms")).sendKeys(keyword);
        driver.findElement(By.xpath("//input[@value='Search']")).click();
    }

    public void openFirstProduct() {
        WebElement firstProduct = driver.findElement(By.linkText("Build your own computer"));
        firstProduct.click();
    }

    public void selectProcessor(String visibleText) {
        WebElement dropdown = driver.findElement(By.id("product_attribute_16_5_4"));
        Select select = new Select(dropdown);
        select.selectByVisibleText(visibleText);
        System.out.println("Selected processor: " + select.getFirstSelectedOption().getText());
    }

    public void selectRam(String value) {
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"product_attribute_16_6_5\"]"));

        Select select = new Select(dropdown);
        select.selectByVisibleText(value);
        System.out.println("Selected RAM: " + select.getFirstSelectedOption().getText());
    }

    public void selectHdd(){
     driver.findElement(By.xpath("//*[@id=\"product-details-form\"]/div/div[1]/div[2]/div[6]/dl/dd[3]/ul/li[1]/label")).click();

    };

    public void selectQuantity(int qty){
        WebElement quantity = driver.findElement(By.id("addtocart_16_EnteredQuantity"));
         for(int i=0; i<=qty; i++){
             quantity.clear();
             quantity.sendKeys(String.valueOf(i));
         }
         }

         public void adtoCard(){
           WebElement addtoCard = driver.findElement(By.id("add-to-cart-button-16"));
           addtoCard.click();
         }
    // ----- Main Test Flow -----
    public void runTest() throws InterruptedException {
        setUp();

        // Register
        register("Afazul", "Jaman", "mihol25071@hiepth.com", "mihol25071@hiepth.com");

        // Login
        login("mihol25071@hiepth.com", "mihol25071@hiepth.com");

        // Search and select product
        searchProduct("computer");
        openFirstProduct();
        selectProcessor("2.2 GHz Intel Pentium Dual-Core E2200");
        selectRam("4GB [+20.00]"); // Example value
        selectHdd();
        selectQuantity(30);
        adtoCard();
        Thread.sleep(3000);
        tearDown();

    }

    // ----- Main Method -----
    public static void main(String[] args) throws InterruptedException {
        AppTest test = new AppTest();
        test.runTest();
    }
}
