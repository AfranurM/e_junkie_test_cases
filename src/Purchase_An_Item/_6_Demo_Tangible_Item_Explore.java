package Purchase_An_Item;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class _6_Demo_Tangible_Item_Explore {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "/Library/Selenium/chromedriver");

        WebDriver driver = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver,10);

        JavascriptExecutor js = (JavascriptExecutor) driver;

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("https://shopdemo.e-junkie.com/");

        List<String> windowHandlesList = new ArrayList<>();

        String originalWindow = driver.getWindowHandle();

        WebElement demoTangibleItem = driver.findElement(By.cssSelector("div[class='thumbnail']"));
        demoTangibleItem.click();

        WebElement imgWithBear = driver.findElement(By.xpath("(//div[@class='EJ-ProductThumbnailContainer']//img)[2]"));
        imgWithBear.click();

        WebElement imgWithFriends = driver.findElement(By.xpath("(//div[@class='EJ-ProductThumbnailContainer']//img)[3]"));
        imgWithFriends.click();

        WebElement moreDetailsButton = driver.findElement(By.className("EJ-ProductDetailUrl"));
        js.executeScript("arguments[0].scrollIntoView();", moreDetailsButton);
        moreDetailsButton.click();

        Set<String> windows = driver.getWindowHandles();
        for (String handle : windows) {
            driver.switchTo().window(handle);
        }

        wait.until(ExpectedConditions.urlToBe("https://www.e-junkie.com/wiki/selling-tangible-items.htm"));

        WebElement sellingTangibleHeader = driver.findElement(By.xpath("//h2[text()='Selling Tangible Goods with E-junkie']"));
        String sellingTangibleHeaderText = sellingTangibleHeader.getText();

        if(sellingTangibleHeaderText.equals("Selling Tangible Goods with E-junkie"))
            System.out.println("you have successfully accessed 'Selling Tangible Goods' page and got the " + sellingTangibleHeaderText + "text");

        else
            System.out.println("there is problem to access the 'Selling Tangible Goods' page");

        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

        driver.close();

        driver.switchTo().window(originalWindow);

        WebElement addToCart = driver.findElement(By.cssSelector("button[type='submit']"));
        addToCart.click();

        WebElement iframe = driver.findElement(By.xpath("//iframe[@class='EJIframeV3 EJOverlayV3']"));
        wait.until(ExpectedConditions.visibilityOf(iframe));
        driver.switchTo().frame(iframe);

        WebElement countryDropdown = driver.findElement(By.cssSelector("select[class='Shipping-Section-Country']"));
        wait.until(ExpectedConditions.visibilityOf(countryDropdown));
        Select select = new Select(countryDropdown);
        select.selectByVisibleText("Belgium");

        WebElement zipCode = driver.findElement(By.cssSelector("input[class='Shipping-Section-Postcode']"));
        zipCode.sendKeys("98765");

        WebElement payUsingPayPal = driver.findElement(By.cssSelector("button[class='Payment-Button Paypal']"));
        payUsingPayPal.click();

        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!windowHandlesList.contains(handle))
                windowHandlesList.add(handle);
        }

        driver.switchTo().window(windowHandlesList.get(1));

        wait.until(ExpectedConditions.urlContains("paypal.com"));

        if(driver.getCurrentUrl().contains("paypal.com"))
            System.out.println("you have successfully been transferred to official PayPal page, please enter your email and password to complete the order!");
        else
            System.out.println("Please try again!!");

        driver.quit();

    }
}
