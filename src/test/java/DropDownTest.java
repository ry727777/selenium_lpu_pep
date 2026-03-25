import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.openqa.selenium.*;
import java.util.List;

public class DropDownTest {

     WebDriver driver;

    public boolean isAlertPresent(WebDriver driver) {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    @BeforeMethod
    public void Setup(){
       driver = new ChromeDriver();
    }

    @Test(groups = {"ContextMenu", "Regression"})
    public void ContextMenu(){
        driver.get("https://the-internet.herokuapp.com/context_menu");
        String text = driver.findElement(By.tagName("h3")).getText();

        Assert.assertTrue(text.contains("Context Menu"), "Website not loaded perfectally");

        // verify no alert will be there 
        Assert.assertFalse(isAlertPresent(driver), "Alert should not present");

        // Right click
        WebElement box = driver.findElement(By.id("hot-spot"));
        Actions action = new Actions(driver);
        action.contextClick(box).perform();

        Assert.assertTrue(isAlertPresent(driver), "Alert should present");

        Alert alert = driver.switchTo().alert();
        String actualtext = alert.getText();

        Assert.assertEquals(actualtext, "You selected a context menu", "Missmatch");
        alert.accept();


        Assert.assertFalse(isAlertPresent(driver), "Alert should not present");

    }

    @Test(groups = {"DissApp", "Regression"})
    public void DisappEle(){
        driver.get("https://the-internet.herokuapp.com/disappearing_elements");
        String text = driver.findElement(By.tagName("h3")).getText();
        Assert.assertEquals(text, "Disappearing Elements", "Some Error");

        boolean isGallaryFound = false;
        int maxRetries = 6;

        for(int i=0; i<6; i++){

            List<WebElement> items = driver.findElements(By.xpath("//ul/li/a"));
            // check for the Gallery

            for(WebElement ele : items){
                if(ele.getText().equals("Gallery")){
                    isGallaryFound = true;
                    break;
                }
            }

            if(isGallaryFound){
                break;
            }

            driver.navigate().refresh();
        }

        Assert.assertTrue(isGallaryFound, "Gallery element did not appear");
    }

    @Test(groups = {"DropDown", "Regression"})
    public void DropDown(){

    }

    @AfterMethod
    public void EndUp(){
        driver.quit();
    }
}
