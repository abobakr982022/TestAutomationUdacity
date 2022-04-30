import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class loginTest {
    public WebElement usernameEl(WebDriver driver){
        By usernamee = By.name("username");
        WebElement username = driver.findElement(usernamee);
        return username;
    }

    public WebElement passwordEl(WebDriver driver){
        By passwordd = By.name("password");
        WebElement password= driver.findElement(passwordd);
        return password;
    }
}
