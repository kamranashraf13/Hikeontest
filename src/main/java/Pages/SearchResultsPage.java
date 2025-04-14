package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.*;

public class SearchResultsPage {
    WebDriver driver;
    WebDriverWait wait;

    By filterStars = By.xpath("//span[text()='4 Stars & Up']");
    By titles = By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']");
    By prices = By.xpath("//span[@class='a-price-whole']");
    By ratings = By.xpath("//span[contains(@class,'a-icon-alt')]");
    By reviews = By.xpath("//span[@class='a-size-base s-underline-text']");

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void applyFilter() {
        wait.until(ExpectedConditions.elementToBeClickable(filterStars)).click();
    }

    public List<WebElement> getProducts() {
        return driver.findElements(titles);
    }

    public List<String[]> extractProductData(double minPrice, double maxPrice) {
        List<String[]> productData = new ArrayList<>();
        List<WebElement> titleElems = driver.findElements(titles);
        List<WebElement> priceElems = driver.findElements(prices);
        List<WebElement> ratingElems = driver.findElements(ratings);
        List<WebElement> reviewElems = driver.findElements(reviews);

        int count = Math.min(Math.min(titleElems.size(), priceElems.size()), ratingElems.size());

        for (int i = 0; i < count; i++) {
            try {
                String title = titleElems.get(i).getText();
                double price = Double.parseDouble(priceElems.get(i).getText().replace(",", ""));
                double rating = Double.parseDouble(ratingElems.get(i).getText().split(" ")[0]);
                String reviewCount = reviewElems.get(i).getText();

                if (price >= minPrice && price <= maxPrice && rating >= 4.0) {
                    productData.add(new String[]{title, String.valueOf(price), String.valueOf(rating), reviewCount});
                }
            } catch (Exception e) {
                System.out.println("Skipping product due to error: " + e.getMessage());
            }
        }

        return productData;
    }

    public void clickFirstProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(titles)).click();
    }
}
