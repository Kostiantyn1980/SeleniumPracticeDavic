import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DavikTest {

    private static final String HOME_PAGE_URL = "https://www.facebook.com/";
    private static WebDriver driver;

    @BeforeAll
    public static void classSetup() {
        driver = SharedDriver.getWebDriver();
        driver.get("https://www.daviktapes.com/");
    }

    @AfterAll
    public static void classTearDown() {
        SharedDriver.closeDriver();
    }

    @AfterEach
    public void testTeardown() {
        driver.get("https://www.daviktapes.com/");
    }

    @Test
    public void movingCursorOnCompanyMenu() throws InterruptedException {
        hoverOverMenu("Company");
        Thread.sleep(1000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement subMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Company']/following-sibling::ul")));
        assertTrue(subMenu.isDisplayed(), "The submenu is not visible.");
        pause();
    }

    @Test
    public void movingCursorOnProducts() throws InterruptedException {
        hoverOverMenu("Products");
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement subMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Products']/following-sibling::ul")));
        assertTrue(subMenu.isDisplayed(), "The submenu is not visible.");
        pause();
    }

    @Test
    public void movingCursorOnIndustries() throws InterruptedException {
        hoverOverMenu("Industries");
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement subMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Industries']/following-sibling::ul")));
        assertTrue(subMenu.isDisplayed(), "The submenu is not visible.");
        pause();
    }

    @Test
    public void movingCursorOnKnowledgeCenter() throws InterruptedException {
        hoverOverMenu("Knowledge Center");
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement subMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Knowledge Center']/following-sibling::ul")));
        assertTrue(subMenu.isDisplayed(), "The submenu is not visible.");
        pause();
    }

//    @Test
//    public void movingCursorOnContact() throws InterruptedException {
//        hoverOverMenu("CONTACT");
//        Thread.sleep(2000);
//        checkSubMenuVisibility();
//        pause();
//    }
//@Test
//public void waitAndClickTestContact() throws InterruptedException {
//    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='CONTACT']"))).click();
//    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='CONTACT']"))).click();
//    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='CONTACT']"))).click();
//    Thread.sleep(2000);
//    WebElement contactMenu = driver.findElement(By.xpath("//*[text()='CONTACT']/following-sibling::ul"));
//    assertTrue(contactMenu.isDisplayed(), "The 'CONTACT' menu is not visible.");
//    pause();
//}

    public void hoverOverMenu(String menuText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='" + menuText + "']")));

        WebElement menuElement = driver.findElement(By.xpath("//*[text()='" + menuText + "']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(menuElement).perform();


        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[text()='" + menuText + "']/following-sibling::ul"))
        );
    }



    public void pause() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Something went wrong");
        }
    }
}
