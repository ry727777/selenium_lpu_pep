
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ABTesting {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get("https://the-internet.herokuapp.com/abtest");

        // capture heading text
        WebElement heading = driver.findElement(By.tagName("h3"));
        // System.out.println(heading);
        String text = heading.getText();
        System.out.println("Page Heading: " + text);

        if(text.contains("Variation 1")){
            System.out.println("variation 1 is displayed");
        }else if(text.contains("Control")){
            System.out.println("Control version disaplayed");
        }else{
            System.out.println("Unknow version");
        }

        // close the browser
        driver.quit();

    }
}
