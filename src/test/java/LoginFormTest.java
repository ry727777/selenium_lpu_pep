

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginFormTest {
    WebDriver driver;

    String url = "https://the-internet.herokuapp.com/login";
    String user_name = "tomsmith";
    String pass = "SuperSecretPassword!";
    By usernameField = By.id("username");
    By passwordField = By.id("password");
    By loginButton = By.cssSelector("button[type = 'submit']");
    // By message = By.id("flash");

    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        driver = new ChromeDriver();
        driver.get(url);
    }

    public void login(String username, String password){
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(username);

        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);

        driver.findElement(loginButton).click();
    }

    public String waitForMessage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement flashMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
        return flashMessage.getText();
    }
    
    @Test(groups = "LoginForm")
    public void correctUserPass(){
        // both should be correct 
        login(user_name, pass);
        String msg = waitForMessage();
        Assert.assertTrue(msg.contains("You logged into a secure area!"), "Some error");
    }

    @Test(groups = {"LoginForm", "corrUser"})
    public void validUsername(){
        // password invalid
        login(user_name, "wrong_password");

        String msg = waitForMessage();
        // Your password is invalid!
        Assert.assertTrue(msg.contains("Your password is invalid!"), "Some error");
    }

    @Test(groups = "LoginForm")
    public void validPassword(){
        // username invalid
        login("wrong_username", pass);
        String msg = waitForMessage();
        // Your username is invalid!
        Assert.assertTrue(msg.contains("Your username is invalid!"), "Some error");
    }

    @Test(groups = "LoginForm")
    public void invalidUserPass(){
        // invalid user name and password both
        login("invalid_username", "invalid_pass");
        String msg = waitForMessage();
        Assert.assertTrue(msg.contains("Your username is invalid!"), "Some error");
    }

    @Test(groups = "LoginForm")
    public void noInput(){
        // no input given
        login("", "");
        String msg = waitForMessage();
        Assert.assertTrue(msg.contains("Your username is invalid!"), "Some error");
    }

    @AfterMethod(alwaysRun = true)
    public void endUp(){
        driver.quit();
    }
}
