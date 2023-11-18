//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//import java.util.concurrent.TimeUnit;
//
//public class SharedDriver {
//
//    private static WebDriver webDriver;
//
//    public static WebDriver getWebDriver() {
//        if (webDriver == null) {
//
//            WebDriverManager.chromedriver().setup();
//
//            webDriver = new ChromeDriver();
//
//            webDriver.manage().window().maximize();
//
//            webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//
//
//        }
//        return webDriver;
//    }
//
//    public static void closeDriver() {
//        if (webDriver != null) {
//            webDriver.close();
//        }
//    }
//
//
//}

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class SharedDriver {

    private static WebDriver webDriver;

    public static WebDriver getWebDriver() {
        if (webDriver == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--headless");

            webDriver = new ChromeDriver(options);
            webDriver.manage().window().maximize();
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//            ChromeOptions options = new ChromeOptions();
//                options.addArguments("--no-sandbox");
//                options.addArguments("--disable-dev-shm-usage");
//                options.addArguments("--headless");
//
//            WebDriverManager.chromedriver().setup();
//            webDriver = new ChromeDriver(options);
        }
        return webDriver;
    }

    public static void closeDriver() {
        if (webDriver != null) {
            webDriver.close();
        }
    }
}
