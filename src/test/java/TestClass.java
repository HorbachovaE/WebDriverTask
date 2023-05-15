import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestClass {
    WebDriver driver;
    By search = By.id("twotabsearchtextbox");
    By shoesLeftLink = By.xpath("//span[text()=\"Women's Walking Shoes\"]");
    By womenShoes = By.xpath("//*[@id=\"searchDropdownBox\"]/option[1]");
    @BeforeTest
    public void callSetUp()
    {
      // System.setProperty("webdriver.chrome.driver", "/Users/evelinahorbachova/IdeaProjects/chromedriver_mac64");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://amazon.com");
    }
    @AfterTest
    public void close()
    {
        driver.quit();
    }

    @Test
    public void amazonSearch()
    {
       // WebElement search = driver.findElement(By.id("twotabsearchtextbox"));
        WebElement findseach = driver.findElement(search);
        findseach.sendKeys("Shoes");
        findseach.submit();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("shoes"), "Opened page should be 'shoes'");
        WebElement leftlink = driver.findElement(shoesLeftLink);
        leftlink.click();
        WebElement appliedLink = driver.findElement(womenShoes);
        String getTextLink = appliedLink.getText();
        Assert.assertEquals(getTextLink, "Women's Walking Shoes", "Women's Walking Shoes department should be applyed");
    }
}