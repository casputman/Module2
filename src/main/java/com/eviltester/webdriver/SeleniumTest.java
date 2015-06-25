package com.eviltester.webdriver;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumTest {

	@Test
	public void startWebDriver(){
		WebDriver driver = new FirefoxDriver();
		driver.navigate().to("http://localhost:8080/ubercoaching/start");
		Assert.assertTrue("title should start with Ubercoaching", driver.getTitle().startsWith("Ubercoaching"));
		driver.close();
		driver.quit();
	}
}
