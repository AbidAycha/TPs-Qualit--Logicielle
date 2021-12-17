import java.io.File;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Tp3 {
    WebDriver driver;

    @BeforeAll
    public static void initialize() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void prepareDriver(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().setScriptTimeout(Duration.ofMinutes(3));

    }
    public static void takeScreenshot(WebDriver webdriver,String fileWithPath) throws Exception{
        
        TakesScreenshot screenShot =((TakesScreenshot)webdriver);
        
        File screenFile=screenShot.getScreenshotAs(OutputType.FILE);
        
        File destinationFile=new File(fileWithPath);

        FileUtils.copyFile(screenFile, destinationFile);

    }
    @Test
    public void todoTestCase() throws Exception {
        driver.get("https://todomvc.com");
        chooseTech("Backbone.js");
        addTodo("Study");
        addTodo("Eat");
        addTodo("Clean");
        removeTodo(2);
        removeTodo(1);
        Thread.sleep(2000);
        assertLeft(1);

        takeScreenshot(driver,
                "C:\\Users\\Miss_A\\IdeaProjects\\Tp3\\ScreenShots\\screenshot.png"); ;
    }
    @ParameterizedTest
    @ValueSource(strings = {"Backbone.js",
            "AngularJS",
            "Dojo",
            "React"})
    public void todosTestCase(String tech) throws Exception {
        driver.get("https://todomvc.com");
        chooseTech(tech);
        addTodo("Study");
        addTodo("Eat");
        addTodo("Clean");
        removeTodo(2);
        removeTodo(1);
        assertLeft(1);
        takeScreenshot(driver, "C:\\\\Users\\\\Miss_A\\\\IdeaProjects\\\\Tp3\\\\ScreenShots\\\\screenshot" + tech +".png");

    }

    private void chooseTech(String tech) {
        WebElement element = driver.findElement(By.linkText(tech));
        element.click();
    }
    private void addTodo(String todo) throws InterruptedException {
        WebElement element = driver.findElement(By.className("new-todo"));
        element.sendKeys(todo);
        element.sendKeys(Keys.RETURN);
        Thread.sleep(2000);
    }
    private void removeTodo(int num) throws InterruptedException {
        WebElement element = driver.findElement(By.cssSelector("li:nth-child("+num+") .toggle"));
        element.click();
        Thread.sleep(2000);
    }
    private void assertLeft(int expectedLeft) throws InterruptedException {
        WebElement element = driver.findElement(By.xpath("//footer/*/span | //footer/span"));
        String expectedTest = String.format("$d item left", expectedLeft);
        validateInnerText(element,expectedTest);
        Thread.sleep(2000);
    }

    private void validateInnerText(WebElement element, String expectedTest) {
        ExpectedConditions.textToBePresentInElement(element, expectedTest);
    }

    @AfterEach
    public void quitDriver() throws InterruptedException {
        driver.quit();
    }
}