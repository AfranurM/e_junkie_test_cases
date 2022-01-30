package Purchase_An_Item;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class _4_PaySuccessfully {

    public static void main(String[] args) {

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

        WebElement emailInput = driver.findElement(By.cssSelector("input[placeholder='Email']"));
        wait.until(ExpectedConditions.visibilityOf(emailInput));
        emailInput.sendKeys("test@email.com");

        WebElement confirmEmail = driver.findElement(By.cssSelector("input[placeholder='Confirm Email']"));
        confirmEmail.sendKeys("test@email.com");

        WebElement name = driver.findElement(By.cssSelector("input[placeholder='Name On Card']"));
        name.sendKeys("Brian");

        WebElement phone = driver.findElement(By.cssSelector("input[autocomplete='phone']"));
        phone.sendKeys("123456789");

        WebElement company = driver.findElement(By.cssSelector("input[autocomplete='company']"));
        company.sendKeys("TechnoStudy");

        WebElement iframe2 = driver.findElement(By.xpath("//div[@class='__PrivateStripeElement']//iframe"));
        wait.until(ExpectedConditions.invisibilityOf(payUsingDebitCard));
        driver.switchTo().frame(iframe2);

        WebElement cardNumberInput = driver.findElement(By.xpath("//span[@class='InputContainer']//input"));
        wait.until(ExpectedConditions.invisibilityOf(payUsingDebitCard));
        cardNumberInput.sendKeys("4242 4242 4242 4242");

        WebElement expDate = driver.findElement(By.cssSelector("input[name='exp-date']"));
        expDate.sendKeys("12/22");

        WebElement cvc = driver.findElement(By.cssSelector("input[name='cvc']"));
        cvc.sendKeys("000");

        driver.switchTo().defaultContent();
        driver.switchTo().frame(iframe);
        WebElement payButton = driver.findElement(By.cssSelector("button[class='Pay-Button']"));
        wait.until(ExpectedConditions.invisibilityOf(payUsingDebitCard));
        payButton.click();

        driver.switchTo().defaultContent();

        wait.until(ExpectedConditions.urlContains("https://www.fatfreecartpro.com"));

        WebElement tyMessage = driver.findElement(By.xpath("//p[@class='confirme_text']//span"));
        wait.until(ExpectedConditions.visibilityOf(tyMessage));

        if(tyMessage.getText().equals("your order is confirmed. Thank you!")) {
            System.out.println("congrats! order confirmed successfully!");
        }

        else
        System.out.println("order could not be completed...");

        driver.quit();

    }
}
