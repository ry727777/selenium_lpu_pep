
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.util.List;

public class ChallDomTest {
    
    WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void Setup(){
        driver = new ChromeDriver();
    }

    @Test(groups = {"ChallDom", "Regression"})
    public void ChallDom(){
        driver.get("https://the-internet.herokuapp.com/challenging_dom");

        String text = driver.findElement(By.tagName("h3")).getText();

        Assert.assertTrue(text.contains("Challenging"), "Website not loaded perfectally");

        List<WebElement>  buttons = driver.findElements(By.xpath("//div[@class=\"large-2 columns\"]/a"));

        Assert.assertEquals(buttons.size(), 3);

        // click on first button
        buttons.get(0).click();

        List<WebElement> table_data =  driver.findElements(By.xpath("//table/tbody/tr"));

        Assert.assertEquals(table_data.size(), 10, "Table size i not equals to 10");

    }

    @Test(groups = {"checkbox", "Regression"})
    public void CheckBox(){
        driver.get("https://the-internet.herokuapp.com/checkboxes");

        String text = driver.findElement(By.tagName("h3")).getText();
        Assert.assertTrue(text.contains("Checkboxes"));

        WebElement checkbox1 = driver.findElement(By.xpath("//form[@id=\"checkboxes\"]/input[1]"));
        WebElement checkbox2 = driver.findElement(By.xpath("//form[@id=\"checkboxes\"]/input[2]"));

        // validate default state
        Assert.assertFalse(checkbox1.isSelected(), "Checkbox 1 is Checked");
        Assert.assertTrue(checkbox2.isSelected(), "Checkbox 2 is Unchecked");

        // System.out.println("Checkbox check " + checkbox1.isSelected());

        if(!checkbox1.isSelected()){
            checkbox1.click();
        }

        if(checkbox2.isSelected()){
            checkbox2.click();
        }

        // validate final one
        Assert.assertTrue(checkbox1.isSelected(), "Checkbox 1 is Checked");
        Assert.assertFalse(checkbox2.isSelected(), "Checkbox 2 is Unchecked");
    }



    @AfterMethod(alwaysRun = true)
    public void WorkDone(){
        driver.quit();
    }

}
