package amazon;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BetterAmazonSearchTest {

    private FirefoxDriver driver;

    @Before
    public void setupSelenium() {
        driver = new FirefoxDriver();
    }


    @Test
    public void search_amazon() throws InterruptedException {
        AmazonHomePage homePage = AmazonHomePage.navigateTo(driver);
        AmazonSearchResultsPage resultsPage = homePage.searchFor("iain banks");
        assertThat(resultsPage.getTopResultTitle(), is("Amazon's Iain Banks Page"));
    }

    @After
    public void closeSelenium() {
        driver.close();
        driver.quit();
}
}
