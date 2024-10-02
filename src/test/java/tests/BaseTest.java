package tests;


import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

//Base Test is used to Setup and Work with WebDriver


public class BaseTest {
	protected WebDriver driver;
	
	@BeforeMethod
	public void Setup() {
		//Setting Up ChromeDriver using WebDriver Manager
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		//Maximize the Window Size
		driver.manage().window().maximize();
		
		//Setting of Wait - Implicitly wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Navigate To Home Page
		driver.get("https://www.entrata.com/c");
		
		DeclineCookie();
		
	}
	@AfterMethod
	public void closeBrowser() {
		if(driver!=null) {
			//Closing the Browser
			driver.quit();
		}
	}
	
	public void DeclineCookie() {
		WebElement cookie = driver.findElement(By.cssSelector("#cookie-policy"));
		if(cookie.isDisplayed()) {
			driver.findElement(By.cssSelector("#cookie-decline")).click();
		}
	}
	public void windowHandling() {
		Set<String> windowIds = driver.getWindowHandles();
		List<String> windowList = new ArrayList(windowIds);
		String parentPage = windowList.get(0);
		String childePage = windowList.get(1);
		driver.switchTo().window(childePage);	
	}
}
