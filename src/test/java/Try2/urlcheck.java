import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class UrlStatusCheck {

	WebDriver driver;

	@Before
	public void setUp() {
		driver = new FirefoxDriver();
		driver.navigate().to("https://www.chegg.com/tutors");
	}

	@Test
	public void checkStatusCode() throws IOException {

		int count = 0;

		List<WebElement> subjects = driver.findElements(
				By.cssSelector("div[id='subject-help-links'] div[class='section'] a[href *='online-tutoring/']"));
		System.out.println(subjects.size());
		List<String> urls = new ArrayList<String>();
		for (WebElement w : subjects) {
			String s = w.getAttribute("href");
			urls.add(s);
		}
		for (String x : urls) {
			URL url = new URL(x);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();

			int code = connection.getResponseCode();
			if (code == 400 || code == 404 || code == 500 || code == 502 || code == 503) {
				count++;
			} else {
				System.out.println("All gravy here: " + url);
			}
			Assert.assertEquals(0, count);
			if (count > 0) {
				System.out.println("Problem here: " + url);
			}
		}
	}

	@After
	public void tearDown() {
		driver.close();
		driver.quit();
	}
}
