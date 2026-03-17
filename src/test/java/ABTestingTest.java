import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ABTestingTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/abtest");
    }

    @Test
    public void verifyABTestVariation() {

        // Capture heading text
        WebElement heading = driver.findElement(By.tagName("h3"));
        String text = heading.getText();

        System.out.println("Page Heading: " + text);

        // Assertion (important)
        Assert.assertTrue(
                text.contains("Variation 1") || text.contains("Control"),
                "Unexpected variation displayed!"
        );
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}