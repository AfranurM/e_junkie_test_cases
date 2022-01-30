package Purchase_An_Item;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class _1_ValidateInvalidPromoCode {

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

        WebElement applyPromoCodeButton = driver.findElement(By.cssSelector("button[class='Apply-Button Show-Promo-Code-Button']"));
        wait.until(ExpectedConditions.visibilityOf(applyPromoCodeButton));
        applyPromoCodeButton.click();

        WebElement promoCodeInput = driver.findElement(By.cssSelector("input[class='Promo-Code-Value']"));
        wait.until(ExpectedConditions.visibilityOf(promoCodeInput));
        promoCodeInput.sendKeys("123456789");

        WebElement applyButton = driver.findElement(By.cssSelector("button[class='Promo-Apply']"));
        applyButton.click();

        WebElement invalid = driver.findElement(By.xpath("//span[text()='Invalid promo code']"));
        String invalidText = invalid.getText();

        if(invalid.getText().contains("Invalid promo code"))
            System.out.println("you have successfully get the \"" + invalidText + "\" message");
        else
            System.out.println("text is not matching with " + invalidText);

        driver.quit();
    }
}
