import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Tp2 {
    public static void main (String args []) throws InterruptedException

    {   WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(3));
        driver.get("https://www.tunisianet.com.tn/");
        Thread.sleep(2000);
        WebElement element = driver.findElement(By.cssSelector("#_desktop_user_info > div > div > svg"));
        element.click();
        Thread.sleep(2000);
        element = driver.findElement(By.cssSelector(".user-down > li > a > span"));
        element.click();
        Thread.sleep(2000);
        element = driver.findElement(By.name("email"));
        element.sendKeys("aicha.abid@insat.ucar.tn");
        element = driver.findElement(By.name("password"));
        element.sendKeys("azertyqwerty");
        element = driver.findElement(By.id("submit-login"));
        element.click();
        Thread.sleep(2000);
        element = driver.findElement(By.className("search_query"));
        element.sendKeys("PC portable MacBook M1 13.3");
        element = driver.findElement(By.cssSelector("#sp-btn-search > button"));
        element.click();
        List<WebElement> productsTitle = driver.findElements(By.className("product-title"));
        productsTitle.get(0).click();
        element = driver.findElement(By.className("add-to-cart"));
        element.click();
        element = driver.findElement(By.cssSelector("a.btn-block"));
        element.click();
        Thread.sleep(2000);
        element = driver.findElement(By.cssSelector(".checkout > div > a"));
        element.click();
        Thread.sleep(2000);
        element = driver.findElement(By.name("address1"));
        element.sendKeys("megrine, Ben Arous");
        element = driver.findElement(By.name("postcode"));
        element.sendKeys("2033");
        element = driver.findElement(By.name("city"));
        element.sendKeys("Ben Arous");
        element = driver.findElement(By.name("phone"));
        element.sendKeys("98765432");
        element = driver.findElement(By.name("confirm-addresses"));
        element.click();
        Thread.sleep(2000);
        element = driver.findElement(By.name("confirmDeliveryOption"));
        element.click();
        Thread.sleep(2000);
        element = driver.findElement(By.id("payment-option-1"));
        element.click();
        element = driver.findElement(By.id("conditions_to_approve[terms-and-conditions]"));
        element.click();
        driver.quit();
    }
}