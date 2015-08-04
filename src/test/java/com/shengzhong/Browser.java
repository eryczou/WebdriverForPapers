package com.shengzhong;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class Browser {
	Browser(){}
	
	static WebDriver getDriver(String browserName) {
		WebDriver driver = null;
		if("CHROME".equals(browserName.toUpperCase())) {
			System.setProperty("webdriver.chrome.driver", "C://chromedriver_win32//chromedriver.exe");
			driver = new ChromeDriver();
		} else if ("FIREFOX".equals(browserName.toUpperCase())) {
			driver = new FirefoxDriver();
		}
		return driver;
	} 
	
	static void getURL(WebDriver driver, String url) {
		driver.get(url);
	}
	
	static void goToCertainPage(WebDriver driver, int num) throws InterruptedException {
		WebElement pageNumberInput = driver.findElement(By.cssSelector("#pageno"));
		pageNumberInput.clear();
		pageNumberInput.sendKeys(num+"");
		pageNumberInput.sendKeys(Keys.ENTER);
		Thread.sleep(4000);
	}
	
	static void openNewPages(WebDriver driver, List<WebElement> webList, int num) {
		Actions builder = new Actions(driver);
		builder.contextClick(webList.get(num)).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
	}
}
