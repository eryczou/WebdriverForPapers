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
	
	static String getJournalAndYear(WebDriver driver) {
		WebElement journalAndYearInfo = driver.findElement(By.cssSelector(".cit"));
		String strJournalAndYear = journalAndYearInfo.getText();
		return strJournalAndYear==null?"No Value":strJournalAndYear;
	}
	
	static String getAuthorNames(WebDriver driver) {
		WebElement authorNames = driver.findElement(By.cssSelector(".auths"));
		String strAuthorNames = authorNames.getText();
		strAuthorNames = strAuthorNames.replaceAll("\\d", "");
		return strAuthorNames==null?"No Value":strAuthorNames;
	}

	static String calculateYear(String input) {
		String result = "Unknown";
		if (input.contains("2")) {
			int firstIndex = input.indexOf("2");
			result = input.substring(firstIndex, firstIndex+4);
		}
		
		return result;
	}

	static String getAuthorDetails(WebDriver driver) throws InterruptedException {
		WebElement authorInfo = driver.findElement(By.cssSelector("#maincontent > div > div.rprt_all > div > div.afflist > h3 > a > span.ui-ncbitoggler-master-text"));
		authorInfo.click();
		Thread.sleep(1000);
		List<WebElement> allAuthors = driver.findElements(By.cssSelector(".ui-ncbi-toggler-slave.ui-ncbitoggler.ui-ncbitoggler-slave-open>li"));
		String output = "";
		for(WebElement author : allAuthors) {
			String ou = author.getText();
			output += ou;
		}
		return output;
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
