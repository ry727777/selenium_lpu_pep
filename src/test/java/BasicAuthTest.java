
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.Test;

import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v120.network.Network;
import org.openqa.selenium.devtools.v120.network.model.Headers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class BasicAuthTest {
    
    @Test(groups = "Basicauth")
    public void BasicAuth(){
         

         WebDriver driver = new ChromeDriver();

         driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");

         WebElement message = driver.findElement(By.tagName("p"));

         String text = message.getText();

         Assert.assertTrue(text.contains("Congratulations!"), "Basic auth failed");

         driver.quit();
    }
}
