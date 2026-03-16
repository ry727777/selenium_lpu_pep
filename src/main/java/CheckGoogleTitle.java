import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CheckGoogleTitle{
    public static void main(String[] args) {

        // path of  chome driver
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        
        // Launch chrome
        WebDriver driver = new ChromeDriver();

        // open website
        driver.get("https://www.google.com");

        // print title
        // System.out.println("Title is: " + driver.getTitle());
        if(driver.getTitle().equals("Google")){
            System.out.println("Test case passed.");
        }else{
            System.out.println("Test case failed.");
        }

        // close the browser
        driver.quit();

    }
}