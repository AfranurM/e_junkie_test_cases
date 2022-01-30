package Purchase_An_Item;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class _3_PayWithInvalidCardNumber {

    public static void main(String[] args) throws InterruptedException {


        System.setProperty("webdriver.chrome.driver", "/Library/Selenium/chromedriver");

        WebDriver driver = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver,10);

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("https://shopdemo.e-junkie.com/");

        WebElement addToCart_eBook = driver.findElement(By.xpath("//h4[text()='Demo eBook']/following-sibling::button"));
        addToCart_eBook.click();

        WebElement iframe = driver.findElement(By.xpath("//iframe[@class='EJIframeV3 EJOverlayV3']"));
        wait.until(ExpectedConditions.visibilityOf(iframe));
        driver.switchTo().frame(iframe);

        WebElement payUsingDebitCard = driver.findElement(By.cssSelector("button[class='Payment-Button CC']"));
        wait.until(ExpectedConditions.visibilityOf(payUsingDebitCard));
        payUsingDebitCard.click();

        WebElement iframe2 = driver.findElement(By.xpath("//div[@class='__PrivateStripeElement']//iframe"));
        wait.until(ExpectedConditions.invisibilityOf(payUsingDebitCard));
        driver.switchTo().frame(iframe2);

        WebElement cardNumberInput = driver.findElement(By.xpath("//span[@class='InputContainer']//input"));
        cardNumberInput.sendKeys("1111 1111 1111 1111");

        driver.switchTo().parentFrame();

        WebElement invalidCard = driver.findElement(By.xpath("//span[text()='Your card number is invalid.']"));
        wait.until(ExpectedConditions.visibilityOf(invalidCard));
        String invalidCardText = invalidCard.getText();

        if(invalidCardText.equals("Your card number is invalid."))
            System.out.println("you got the \"" + invalidCardText + "\" message");
        else
            System.out.println("your text is not matching with: " + invalidCardText);

        driver.quit();

    }
}
