package test;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.HomePage;
import Pages.ProductDetailsPage;
import Pages.SearchResultsPage;
import utils.BaseTest;
import utils.CSVutils;

public class Amazontest extends BaseTest {

    @Test
    public void testSearchExtractVerify() {
        HomePage homePage = new HomePage(driver);
        homePage.searchForProduct("wireless headphones");

        SearchResultsPage resultsPage = new SearchResultsPage(driver);
        resultsPage.applyFilter();

        List<String[]> productData = resultsPage.extractProductData(50.0, 200.0);
        Assert.assertFalse(productData.isEmpty(), "No products found matching the criteria.");

        CSVutils.writeCSV(productData, "products.csv");

        resultsPage.clickFirstProduct();

        ProductDetailsPage detailsPage = new ProductDetailsPage(driver);
        Assert.assertTrue(detailsPage.getTitle().contains(productData.get(0)[0]));
        Assert.assertEquals(detailsPage.getPrice(), productData.get(0)[1]);
    }
}
