import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class openBrowser {


     WebDriver driver = null;
    @BeforeTest
    public void openBrowser() throws InterruptedException {
        String chromeDriverPath= System.getProperty("user.dir")+"\\src\\main\\resources\\browsers\\chromedriver.exe";
        System.out.println(chromeDriverPath);
        //link between  testScript and browser
        System.setProperty("webdriver.chrome.driver",chromeDriverPath);
        //create object
        driver = new ChromeDriver();
        //openbrowser
//        driver.navigate().to("https://the-internet.herokuapp.com/login");
        //maximize
        driver.manage().window().maximize();
        //test script
        Thread.sleep(3000);

    }
    SoftAssert soft = new SoftAssert();
    @Test (priority = 1)
    public void validTest() throws InterruptedException {
        driver.navigate().to("https://the-internet.herokuapp.com/login");
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("username")).sendKeys("tomsmith");
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.name("password")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        String expectedValue = " You logged into a secure area!";
        String actualValue = driver.findElement(By.id("flash")).getText();
        System.out.println(actualValue);
        //First Assertion
        System.out.println("First Assretion");
        soft.assertTrue(expectedValue.contains(actualValue) , "first assertion");
        //Second Assertion
        System.out.println("Second Assertion");
        System.out.println(driver.findElement(By.cssSelector("a[href=\"/logout\"]")).isDisplayed());
        soft.assertFalse(driver.findElement(By.cssSelector("a[href=\"/logout\"]")).isDisplayed(),"Second Assertion");

        //Third Assertion
        System.out.println("Third Assertion");
        soft.assertTrue(driver.getCurrentUrl().contains("the-internet.herokuapp.com/secure"),"Third Assertion");



    }

    @Test(priority = 2)
    public void invalidTest() throws InterruptedException {
        driver.navigate().to("https://the-internet.herokuapp.com/login");
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("username")).sendKeys("tttt");
        driver.findElement(By.name("password")).sendKeys("Super");
        driver.findElement(By.name("password")).sendKeys(Keys.ENTER);
        Thread.sleep(3000);


    }

    @AfterTest
    public void closeBrowser(){
        driver.quit();

    }
}
