package com.shengzhong;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.shengzhong.utility.CountryUtility;
import com.shengzhong.excel.ExcelSheet;

public class MainTest {
	public static void main(String args[]) throws IOException {
		WebDriver driver = Browser.getDriver("FIREFOX");
		ExcelSheet excelSheetObj = null;
		driver.manage().window().maximize();
		try {
			Browser.getURL(driver, "http://www.ncbi.nlm.nih.gov/pubmed");
			String mainTitle = driver.getTitle();
			String mainHanlde = driver.getWindowHandle();
			SearchFunction.doSearch(driver, "loop mediated isothermal amplification");
			Set<String> handles = null;
			excelSheetObj = new ExcelSheet();
			excelSheetObj.createWorkBook("C:\\Users\\Shengzhong\\Desktop\\", "Results.xlsx");
			for(int i=1; i<=10; i++) {
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
		                String title = driver.getTitle();
		                String strJournalAndYearget = Browser.getJournalAndYear(driver);
		                String authors = Browser.getAuthorNames(driver);
		                String year = Browser.calculateYear(strJournalAndYearget);
		                String authorDetails;
		                String country;
		                if (existsElement(driver, "#maincontent > div > div.rprt_all > div > div.afflist > h3 > a > span.ui-ncbitoggler-master-text")) {
			                authorDetails = Browser.getAuthorDetails(driver);
							System.out.println(authorDetails);
							System.out.println("***");
							System.out.println(strJournalAndYearget);
							System.out.println(authors);
		                } else {
		                	authorDetails = "No Authors information";
		                	System.out.println("No Authors information");
		                }
		                System.out.println("-----------------");
		                country = CountryUtility.determineCountry(authorDetails);
		                excelSheetObj.addNewRow(title, strJournalAndYearget, year, authors, authorDetails, country);
						driver.close(); 
		                driver.switchTo().window(mainHanlde);
				    }
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			excelSheetObj.tearDownExcel();
			driver.close();
		}
	}
	
	private static boolean existsElement(WebDriver driver, String css) {
	    try {
	        driver.findElement(By.cssSelector(css));
	    } catch (NoSuchElementException e) {
	        return false;
	    }
	    return true;
	}
}
