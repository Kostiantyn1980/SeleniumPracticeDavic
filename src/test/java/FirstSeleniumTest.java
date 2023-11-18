import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FirstSeleniumTest {

    private static final String HOME_PAGE_URL = "https://www.facebook.com/";

    private static WebDriver driver;

    @BeforeAll
    public static void classSetup() {
        driver = SharedDriver.getWebDriver();
        driver.get(HOME_PAGE_URL);
    }

    @AfterAll
    public static void classTearDown() {
        SharedDriver.closeDriver();

    }

    @AfterEach
    public void testTeardown() {
        driver.get(HOME_PAGE_URL);
    }

    @Test
    public void homePageURLTest() {
        String actualURL = driver.getCurrentUrl();
        assertEquals(HOME_PAGE_URL, actualURL, "URLs do not match");
    }

    @Test
    public void findElementByAttributeTest() {

        //Let`s find the elements by different webdriver find BY methods
        WebElement elementEmailById = driver.findElement(By.id("email"));
        WebElement elementEmailByName = driver.findElement(By.name("email"));
        WebElement elementCreatePageByLinkText = driver.findElement(By.linkText("Create a Page"));
        WebElement elementCreatePageByPartitialLinkText = driver.findElement(By.partialLinkText("a Page"));
        WebElement elementEmailByCss = driver.findElement(By.cssSelector("#email"));
        assertNotNull(elementEmailById);

        //Make sure you have one element, and not many!
        List<WebElement> elementsByClassName = driver.findElements(By.className("inputtext"));
        System.out.println(elementsByClassName.size());
    }

    @Test
    public void findElementsByXpathTest() {
        WebElement emailElement = driver.findElement(By.xpath("//input[@name='email']"));
        assertNotNull(emailElement);
        WebElement passwordElement = driver.findElement(By.xpath("//input[@data-testid='royal_pass']"));
        assertNotNull(passwordElement);
        WebElement loginButtonElement = driver.findElement(By.xpath("//button[@type='submit']"));
        assertNotNull(loginButtonElement);
        WebElement forgotPassElement = driver.findElement(By.xpath("//a[text()='Forgot password?']"));
        assertNotNull(forgotPassElement);
        WebElement createNewAccButton = driver.findElement(By.xpath("//*[text() = 'Create new account']"));
        assertNotNull(createNewAccButton);

    }

    @Test
    public void loginScreenTest() {
        //find email element
        WebElement emailElement = driver.findElement(By.xpath("//input[@name='email']"));

        //verify element found
        assertNotNull(emailElement);

        // send text
        emailElement.sendKeys("Lev.gorfel@gmail.com");

        //get text from browser
        String emailValue = emailElement.getAttribute("value");

        //verify text is correct
        assertEquals("Lev.gorfel@gmail.com", emailValue);

        //same with password
        WebElement passwordElement = driver.findElement(By.xpath("//input[@data-testid='royal_pass']"));
        assertNotNull(passwordElement);
        passwordElement.sendKeys("123456");
        String passValue = passwordElement.getAttribute("value");
        assertEquals("123456", passValue);

        //find login button
        WebElement loginButtonElement = driver.findElement(By.xpath("//button[@type='submit']"));
        assertNotNull(loginButtonElement);

        //click login button
        loginButtonElement.click();

    }

    @Test
    public void signupTest() {
        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));
    }

    @Test
    public void gendersTest() throws InterruptedException {
        String femaleXpath = "//*[@name='sex' and @value=1]";
        String maleXpath = "//*[@name='sex' and @value=2]";

        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));

        Thread.sleep(1000);

        //verify female gender is checked
        WebElement femaleButton = driver.findElement(By.xpath(femaleXpath));
        femaleButton.click();
        String isFemaleChecked = driver.findElement(By.xpath(femaleXpath)).getAttribute("checked");
        assertNotNull(isFemaleChecked);
        assertEquals("true", isFemaleChecked);

        //verify male gender is checked
        WebElement maleButton = driver.findElement(By.xpath(maleXpath));
        maleButton.click();
        String isMaleChecked = driver.findElement(By.xpath(maleXpath)).getAttribute("checked");
        assertNotNull(isMaleChecked);
        assertEquals("true", isMaleChecked);

    }

    @Test
    public void errorMessageTest() throws InterruptedException {
        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@name='websubmit']")).click();
        driver.findElement(By.xpath("//*[@aria-label='Mobile number or email']")).click();

        WebElement error = driver.findElement(By.xpath("//*[contains(text(), 'to reset')]"));
        assertNotNull(error);

    }

    @Test
    public void yearTest() throws InterruptedException {
        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@title='Year']")).click();
        driver.findElement(By.xpath("//*[text() = '1990']")).click();
        String yearValue= driver.findElement(By.xpath("//*[@title='Year']")).getAttribute("value");

        assertEquals("1990", yearValue);


    }

    @ParameterizedTest
    @ValueSource(strings = {"1905", "1950", "2020"})
    public void yearTestParametrized(String yearInput) throws InterruptedException {
        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text()='Sign Up']")));
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@title='Year']")).click();
        driver.findElement(By.xpath("//*[text() = '"+ yearInput +"']")).click();
        String yearValue= driver.findElement(By.xpath("//*[@title='Year']")).getAttribute("value");

        assertEquals(yearInput, yearValue);


    }


}
