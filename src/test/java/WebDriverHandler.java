import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverHandler {
    
    public static WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
        System.setProperty("Selenide.browser", "Chrome");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        //WebDriverRunner.setWebDriver(driver);

        Configuration.timeout = 5000;
        Configuration.holdBrowserOpen = true;
        return driver;

    }
}
