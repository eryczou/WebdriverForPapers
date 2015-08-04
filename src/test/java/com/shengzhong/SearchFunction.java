package com.shengzhong;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchFunction {
	SearchFunction() {}
	
	static void doSearch(WebDriver driver, String inputStr) throws InterruptedException {
		WebElement inputText = driver.findElement(By.xpath("//*[@id=\"term\"]"));
		inputText.sendKeys(inputStr);
		WebElement submitBtn = driver.findElement(By.xpath("//*[@id=\"search\"]"));
		submitBtn.click();
		Thread.sleep(2000);
	}
}
