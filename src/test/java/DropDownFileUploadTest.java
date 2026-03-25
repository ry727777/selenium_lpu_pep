
import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

import org.testng.Assert;

public class DropDownFileUploadTest {

    WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    
    @Test(groups = "dropdown")
    public void dropDown(){

        driver.get("https://the-internet.herokuapp.com/dropdown");
        String text = driver.findElement(By.tagName("h3")).getText();
        Assert.assertEquals(text, "Dropdown List", "Page not loaded");

        // verify the default value
        WebElement dropdownElement = driver.findElement(By.id("dropdown"));
        Select dropdown = new Select(dropdownElement);
        String text1 = dropdown.getFirstSelectedOption().getText();
        Assert.assertEquals(text1, "Please select an option");

        // select a option
        dropdown.selectByVisibleText("Option 1");
        text1 = dropdown.getFirstSelectedOption().getText();
        Assert.assertEquals(text1, "Option 1");

        // select by value for option 2
        dropdown.selectByValue("2");
        text1 = dropdown.getFirstSelectedOption().getText();
        Assert.assertEquals(text1, "Option 2");

        // count all drop down
        int total_option = dropdown.getOptions().size();
        assert total_option == 3;

    }

    @Test(groups = "fileupload")
    public void fileUpload(){
        driver.get("https://the-internet.herokuapp.com/upload");
        String text = driver.findElement(By.tagName("h3")).getText();
        assert text.equals("File Uploader");
        WebElement file_upload = driver.findElement(By.id("file-upload"));

        String file_path = "\"C:\\Users\\rahul\\OneDrive\\Desktop\\dsa-practice\\code_force\\Binary_search\\FastSearch.java\"";

        file_upload.sendKeys(file_path);

        driver.findElement(By.id("file-submit")).click();
        //File Uploaded!
        text = driver.findElement(By.tagName("h3")).getText();
        assert text.equals("File Uploaded!");

        String file_name = driver.findElement(By.id("uploaded-files")).getText();
        assert file_name.equals("FastSearch.java");
    }

    @AfterMethod(alwaysRun = true)
    public void endTest(){
        driver.quit();
    }
}
