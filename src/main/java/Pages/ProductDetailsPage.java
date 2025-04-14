package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage {
    WebDriver driver;

    By title = By.id("productTitle");
    By price = By.cssSelector("span.a-price span.a-offscreen");
    By rating = By.cssSelector("span.a-icon-alt");
    By reviews = By.id("acrCustomerReviewText");

    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        return driver.findElement(title).getText().trim();
    }

    public String getPrice() {
        return driver.findElement(price).getText().replace("$", "").trim();
    }

    public String getRating() {
        return driver.findElement(rating).getText().split(" ")[0];
    }

    public String getReviewCount() {
        return driver.findElement(reviews).getText().split(" ")[0].replace(",", "");
    }
}
