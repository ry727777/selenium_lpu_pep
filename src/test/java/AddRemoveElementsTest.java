
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class AddRemoveElementsTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
    }

    @Test
    public void AddRemoveEle() {
        // click three time
        for(int i=0; i<3; i++){
            driver.findElement(By.xpath("//button[text() = \"Add Element\"]")).click();
        }
        // store all the delete button
        List<WebElement> deleteButtons = driver.findElements(By.className("added-manually"));
        int total_button = deleteButtons.size();
        Assert.assertEquals(total_button, 3);

        // Delete the button and check -> size should be 1
        deleteButtons.get(0).click();
        deleteButtons.get(1).click();
        
        deleteButtons = driver.findElements(By.className("added-manually"));
        total_button = deleteButtons.size();
        Assert.assertEquals(total_button, 1);
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
