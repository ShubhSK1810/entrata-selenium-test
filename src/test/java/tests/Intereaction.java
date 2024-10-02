package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Intereaction extends BaseTest {
	
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	@Test(dataProvider="WatchDemoValid", priority=1)
	public void intereactWithWatchDemoWithValidDetails(String name, String lastname, String gmail, String company, String mob, String job) {
		//Find Watch Demo Button and Click
		WebElement watchDemo = driver.findElement(By.linkText("Watch Demo"));
		wait.until(ExpectedConditions.elementToBeClickable(watchDemo));
		watchDemo.click();
		
		//Check whether we are on right page
		driver.findElement(By.xpath("//*[text()='Optimize Property Management with One Platform']")).isDisplayed();	
		
		//Filling the right details in Form
		driver.findElement(By.id("FirstName")).sendKeys(name);
		driver.findElement(By.id("LastName")).sendKeys(lastname);
		driver.findElement(By.id("Email")).sendKeys(gmail);
		driver.findElement(By.id("Company")).sendKeys(company);
		driver.findElement(By.id("Phone")).sendKeys(mob);
		driver.findElement(By.id("Title")).sendKeys(job);
		
		//Selecting Unit Count From Drop Down
		WebElement unitCount = driver.findElement(By.id("Unit_Count__c"));
		Select selectCount = new Select(unitCount);
		selectCount.selectByValue("1 - 10");
		
		//Selecting DemoRequestor from Drop Down
		WebElement requestor = driver.findElement(By.id("demoRequest"));
		Select selectRequestor = new Select(requestor);
		selectRequestor.selectByVisibleText("a Resident");
		
		//Validate Terms and Condition Message is displaying
		WebElement terms = driver.findElement(By.xpath("//*[@class='mktoCaptchaDisclaimer']"));
		String actualTerms = terms.getText();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", terms);
		Assert.assertTrue(actualTerms.contains("This site is protected by reCAPTCHA and the Google"),"Terms and condition Should display");
		
		//Check Whether Watch Demo Button Displaying or not
		WebElement watchDemoBtn = driver.findElement(By.xpath("//*[@class='mktoButton']"));
		Assert.assertTrue(watchDemoBtn.isDisplayed());
		
	}
	@Test (dataProvider="WatchDemoEmailInValid", priority=2)
	public void intereactWithWatchDemoWithInValidEmail(String name, String lastname, String gmail, String company, String mob, String job, String validErrorEmailMsg, String validMsgEmail) throws InterruptedException {
		//Find Watch Demo Button and Click
		WebElement watchDemo = driver.findElement(By.linkText("Watch Demo"));
		watchDemo.click();
		
		//Check whether we are on right page
		driver.findElement(By.xpath("//*[text()='Optimize Property Management with One Platform']")).isDisplayed();	
		
		driver.findElement(By.id("FirstName")).sendKeys(name);
		driver.findElement(By.id("LastName")).sendKeys(lastname);
		driver.findElement(By.id("Email")).sendKeys(gmail);
		
		//Click on Watch Demo Button
		WebElement watchDemoBtn = driver.findElement(By.xpath("//*[@class='mktoButton']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", watchDemoBtn);
		
		
		//Check Whether Valid Email Error Message is displaying and check the proper message is displaying
		WebElement emailError = driver.findElement(By.id("ValidMsgEmail"));
		Assert.assertTrue(emailError.isDisplayed());
		
		String actualErrorMessage = emailError.getText();
		Assert.assertTrue(actualErrorMessage.contains(validErrorEmailMsg), "Proper Error Message for Invalid Email Messsage is not displaying");
		Assert.assertTrue(actualErrorMessage.contains(validMsgEmail), "Proper Error Message for Invalid Email is not displaying");
		
	}
	@Test(dataProvider="WatchDemoMissingDetail", priority=3)
	public void intereactWithWatchDemoWitoutSelectingountDropDown(String name, String lastname, String gmail, String company, String mob, String job, String missingField) {
		//Find Watch Demo Button and Click
		WebElement watchDemo = driver.findElement(By.linkText("Watch Demo"));
		wait.until(ExpectedConditions.elementToBeClickable(watchDemo));
		watchDemo.click();
		
		//Check whether we are on right page
		driver.findElement(By.xpath("//*[text()='Optimize Property Management with One Platform']")).isDisplayed();	
		
		driver.findElement(By.id("FirstName")).sendKeys(name);
		driver.findElement(By.id("LastName")).sendKeys(lastname);
		driver.findElement(By.id("Email")).sendKeys(gmail);
		driver.findElement(By.id("Company")).sendKeys(company);
		driver.findElement(By.id("Phone")).sendKeys(mob);
		driver.findElement(By.id("Title")).sendKeys(job);
		
		//Selecting DemoRequestor from Drop Down
		WebElement requestor = driver.findElement(By.id("demoRequest"));
		Select selectRequestor = new Select(requestor);
		selectRequestor.selectByVisibleText("a Resident");
		
		//Click on Watch Demo Button
		WebElement watchDemoBtn = driver.findElement(By.xpath("//*[@class='mktoButton']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", watchDemoBtn);
		
		//check Whether Error Message for Unit count Drop Down is displaying and Proper Message displaying or not
		WebElement unitCount = driver.findElement(By.id("ValidMsgUnit_Count__c"));
		Assert.assertTrue(unitCount.isDisplayed());
		
		String actualErrorMsg = unitCount.getText();
		Assert.assertEquals(actualErrorMsg, missingField, "Missing Field Error Message is not displaying for Unit Count Drop Down");
		
	}
	
	@DataProvider (name="WatchDemoValid")
	Object[][] watchDemoValid(){
		Object data [][] = {	{"Shubham","Kumbhar","shubhamsk@gmail.com","Entrata","1222222","Tester"},
						};
		return data;
	}
	@DataProvider (name="WatchDemoEmailInValid")
	Object[][] watchDemoEmailInValid(){
		Object data [][] = {	{"Shubham","Kumbhar","Sk","Entrata","1222222","Tester","Must be valid email.","example@yourdomain.com"}
				};
		return data;
	}
	@DataProvider (name="WatchDemoMissingDetail")
	Object[][] watchDemoNotSelectUnitDropDown(){
		Object data [][] = {	{"Shubham","Kumbhar","shubhamsk@gmail.com","Entrata","1222222","Tester","This field is required."},
						};
		return data;
	}
}
