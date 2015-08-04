package com.shengzhong;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainTest {
	public static void main(String args[]) {
		WebDriver driver = Browser.getDriver("FIREFOX");
		driver.manage().window().maximize();
		try {
			Browser.getURL(driver, "http://www.ncbi.nlm.nih.gov/pubmed");
			String mainTitle = driver.getTitle();
			String mainHanlde = driver.getWindowHandle();
			SearchFunction.doSearch(driver, "loop mediated isothermal amplification");
			Set<String> handles = null;
			for(int i=1; i<=2; i++) {
				if (handles != null) handles.clear();
				Browser.goToCertainPage(driver, i);
				List<WebElement> webList = driver.findElements(By.cssSelector(".title>a"));
				for (int j=0; j<webList.size(); j++) {
					Browser.openNewPages(driver, webList, j);
				}
				handles = driver.getWindowHandles();
				Iterator ite=handles.iterator();
				while(ite.hasNext())
				{
				    String popupHandle=ite.next().toString();
				    if(!popupHandle.contains(mainHanlde))
				    {
		                driver.switchTo().window(popupHandle);
		                Thread.sleep(1000);
		                WebElement authorInfo = driver.findElement(By.cssSelector("#maincontent > div > div.rprt_all > div > div.afflist > h3 > a > span.ui-ncbitoggler-master-text"));
						authorInfo.click();
						Thread.sleep(1000);
						List<WebElement> allAuthors = driver.findElements(By.cssSelector(".ui-ncbi-toggler-slave.ui-ncbitoggler.ui-ncbitoggler-slave-open>li"));
						String output = "";
						for(WebElement author : allAuthors) {
							String ou = author.getText();
							output += ou;
						}
						System.out.println(output);
						System.out.println("-----------------");
						driver.close();
		                driver.switchTo().window(mainHanlde);
				    }
				}
			}
			
//			webList.get(0).click();
//			WebElement webEl = driver.findElement(By.cssSelector("#maincontent > div > div.rprt_all > div > div.afflist > h3 > a > span.ui-ncbitoggler-master-text"));
//			webEl.click();
//			List<WebElement> webList1 = driver.findElements(By.cssSelector(".ui-ncbi-toggler-slave.ui-ncbitoggler.ui-ncbitoggler-slave-open>li"));
//			String output = "";
//			for(int i=0; i<webList1.size(); i++) {
//				String ou = webList1.get(i).getText();
//				output += ou;
//			}
//			System.out.println(output);
//			driver.navigate().back();
//			for (int i=0; i<webList.size(); i++) {
//				Actions builder2 = new Actions(driver);
//				builder2.contextClick(webList.get(i)).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
////				for(String winHandle : driver.getWindowHandles()){
////				    if ( winHandle != mainWindow) {
////				    	driver.switchTo().window(winHandle);
////				    	WebElement webEl = driver.findElement(By.cssSelector("#maincontent > div > div.rprt_all > div > div.afflist > h3 > a > span.ui-ncbitoggler-master-text"));
////				    	webEl.click();
//////				    	for(int j=0; j<webList.size(); j++) {
//////							String ou = webList.get(i).getText() + " ----";
//////							output += ou;
//////						}
//////				    	System.out.println(output);
//////				    	driver.close();
//////				    	driver.switchTo().window(mainWindow);
////				    }
////				}
//			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			driver.close();
		}
	}
}
