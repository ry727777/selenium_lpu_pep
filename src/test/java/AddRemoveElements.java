
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class AddRemoveElements {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");

        // click three time
        for(int i=0; i<3; i++){
            driver.findElement(By.xpath("//button[text() = \"Add Element\"]")).click();
        }

        // store all the delete button
        List<WebElement> deleteButtons = driver.findElements(By.className("added-manually"));

        int total_button = deleteButtons.size();

        if(total_button == 3){
            System.out.println("Test case passed");
        }else{
            System.out.println("Test case failed");
        }

        // close the driver
        driver.quit();
    }
}
