package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class careerFeature extends BaseTest {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	@Test (dataProvider="careerData", priority=1)
	public void exploringJobSite(String expectedCO, String expectedFA, String expectedPr, String expectedRD, String expectedRP) {
		WebElement career = driver.findElement(By.linkText("Careers"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", career);
		js.executeScript("arguments[0].click()", career);

		//Validate we are on Career Page
		DeclineCookie();
		String currentUrlCareer = driver.getCurrentUrl();
		Assert.assertTrue(currentUrlCareer.contains("/careers"), "Career Page URl not contain '/careers'");
		
		//Clicking on Search Jobs Link
		WebElement searchJob = driver.findElement(By.linkText("Search Jobs"));
		js.executeScript("arguments[0].click()", searchJob);
		
		//Validate we are on Jobs Page
		windowHandling();
		String currentUrlJobs = driver.getCurrentUrl();
		Assert.assertTrue(currentUrlJobs.contains("/jobs"), "Career Page URl not contain '/jobs'");
		
		//Selecting From Drop Downs (div) -- Dont have select class
		driver.findElement(By.xpath("//*[text()='Location type']")).click();
		driver.findElement(By.linkText("All")).click();
		
		driver.findElement(By.xpath("//*[text()='Location']")).click();
		driver.findElement(By.linkText("All")).click();
		
		driver.findElement(By.xpath("//*[text()='Team']")).click();
		driver.findElement(By.linkText("All")).click();
		
		driver.findElement(By.xpath("//*[text()='Work type']")).click();
		driver.findElement(By.linkText("All")).click();
		
		//Verify All Types of Team Headers are Displaying
		String actualCO = driver.findElement(By.xpath("//*[@class='large-category-header'][text()='Customer Operations']")).getText();
		Assert.assertEquals(actualCO, expectedCO,"Team Header 'CUSTOMER OPERATIONS' is not displaying");
		
		String actualFA = driver.findElement(By.xpath("//*[@class='large-category-header'][text()='Finance & Accounting']")).getText();
		Assert.assertEquals(actualFA, expectedFA,"Team Header 'FINANCE & ACCOUNTING' is not displaying");
		
		String actualPr = driver.findElement(By.xpath("//*[@class='large-category-header'][text()='Product']")).getText();
		Assert.assertEquals(actualPr, expectedPr,"Team Header 'PRODUCT' is not displaying");
		
		String actualRD = driver.findElement(By.xpath("//*[@class='large-category-header'][text()='Research and Development']")).getText();
		Assert.assertEquals(actualRD, expectedRD,"Team Header 'RESEARCH AND DEVELOPMENT' is not displaying");
		
		String actualRP = driver.findElement(By.xpath("//*[@class='large-category-header'][text()='Revenue Platform']")).getText();
		Assert.assertEquals(actualRP, expectedRP,"Team Header 'REVENUE PLATFORM' is not displaying");
		
	}
	@Test (dataProvider="testEngineerData", priority=2)
	public void exploringTestEngineerPost(String expectedRD, String expectedTE, String expectedFirstPost, String expectedSecondPost, String expectedThirdPost) {
		WebElement career = driver.findElement(By.linkText("Careers"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", career);
		js.executeScript("arguments[0].click()", career);
		
		//Clicking on Search Jobs Link
		WebElement searchJob = driver.findElement(By.linkText("Search Jobs"));
		js.executeScript("arguments[0].click()", searchJob);
		
		//Validate we are on Jobs Page
		windowHandling();
		String currentUrlJobs = driver.getCurrentUrl();
		Assert.assertTrue(currentUrlJobs.contains("/jobs"), "Career Page URl not contain '/jobs'");
		
		//Selecting Test Engineer Job
		driver.findElement(By.xpath("//*[text()='Team']")).click();
		WebElement TE = driver.findElement(By.linkText("Test Engineering"));
		wait.until(ExpectedConditions.elementToBeClickable(TE));
		TE.click();
		
		//Verify Right Team Header and Sub Header Display or not
		WebElement elementTE = driver.findElement(By.xpath("//*[@class='posting-category-title large-category-label'][text()='Test Engineering']"));
		String actualTE = elementTE.getText();
		wait.until(ExpectedConditions.visibilityOf(elementTE));
		Assert.assertEquals(actualTE, expectedTE,"Team Sub Header 'TEST ENGINEERING' is not displaying");
		
		String actualRD = driver.findElement(By.xpath("//*[@class='large-category-header'][text()='Research and Development']")).getText();
		Assert.assertEquals(actualRD, expectedRD,"Team Header 'RESEARCH AND DEVELOPMENT' is not displaying");
		
		
		//Verify all three Job Titles 
		int totalJobRole = driver.findElements(By.xpath("//*[@class='posting-title']")).size();
		Assert.assertEquals(totalJobRole, 3);
		
		//Using SoftAssert
		SoftAssert sa = new SoftAssert();
		
		String actualFirstPost = driver.findElement(By.xpath("//*[text()='SDET Engineer']")).getText();
		sa.assertEquals(actualFirstPost, expectedFirstPost, "'SDET Engineer' post is not displaying");
		
		String actualSecondtPost = driver.findElement(By.xpath("//*[text()='Senior Performance Testing Engineer']")).getText();
		sa.assertEquals(actualSecondtPost, expectedSecondPost, "'Senior Performance Testing Engineer' post is not displaying");
		
		String actualThirdtPost = driver.findElement(By.xpath("//*[text()='Senior SDET Engineer']")).getText();
		sa.assertEquals(actualThirdtPost, expectedThirdPost, "'Senior SDET Engineer' post is not displaying");
		sa.assertAll();	
	}
	@Test(dataProvider="SDETData", priority=3)
	public void applForSDET(String expectedFirstPost, String expectedPQ, String expectedMQ, String expectedSuccessMsg) {
		WebElement career = driver.findElement(By.linkText("Careers"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", career);
		js.executeScript("arguments[0].click()", career);
		
		//Clicking on Search Jobs Link
		WebElement searchJob = driver.findElement(By.linkText("Search Jobs"));
		js.executeScript("arguments[0].click()", searchJob);
		
		//Selecting Test Engineer Job
		windowHandling();
		driver.findElement(By.xpath("//*[text()='Team']")).click();
		WebElement TE = driver.findElement(By.linkText("Test Engineering"));
		wait.until(ExpectedConditions.elementToBeClickable(TE));
		TE.click();
		
		//Check SDET job role displaying
		String actualFirstPost = driver.findElement(By.xpath("//*[text()='SDET Engineer']")).getText();
		Assert.assertEquals(actualFirstPost, expectedFirstPost, "'SDET Engineer' post is not displaying");
		
		//Click on Apply button 
		driver.findElement(By.xpath("(//*[@class='posting-btn-submit template-btn-submit hex-color'])[1]")).click();
		
		//Validate Preferred Qualification an Minimum Qualification is displaying
		String actualPQ = driver.findElement(By.xpath("//*[text()='Preferred Qualifications']")).getText();
		Assert.assertEquals(actualPQ, expectedPQ, "Preferred Qualifications is not displaing");
		
		String actualMQ = driver.findElement(By.xpath("//*[text()='Minimum Qualifications']")).getText();
		Assert.assertEquals(actualMQ, expectedMQ, "Minimum Qualification is not displaing");
		
		//Clicking on Apply for this Job
		WebElement applyForJob = driver.findElement(By.xpath("//*[@class='postings-btn-wrapper']//a"));
		wait.until(ExpectedConditions.elementToBeClickable(applyForJob));
		applyForJob.click();
		
		//Check whether Submit job application is displaying an apply resume
		WebElement applJob = driver.findElement(By.xpath("//*[text()='Submit your application']"));
		Assert.assertTrue(applJob.isDisplayed());
		
		String filePath = System.getProperty("user.dir")+("/document/Resume_Shubham_Kumbhar_Sept.pdf");
		driver.findElement(By.xpath("//*[@class='application-file-input invisible-resume-upload']")).sendKeys(filePath);
		
		//Check whether success message is with icon is displaying or not after resume upload
		WebElement successIcon = driver.findElement(By.xpath("//*[@class='resume-upload-success']"));
		wait.until(ExpectedConditions.visibilityOf(successIcon));
		Assert.assertTrue(successIcon.isDisplayed());
		
		String actualSuccessMsg = driver.findElement(By.xpath("//*[@class='resume-upload-success']/*[@class='resume-upload-label']")).getText();
		Assert.assertEquals(actualSuccessMsg, expectedSuccessMsg, "Success Message is not displaing");
		
		
	}
	@DataProvider (name="careerData")
	Object[][] careerData(){
		Object data [][] = {	{"CUSTOMER OPERATIONS","FINANCE & ACCOUNTING","PRODUCT","RESEARCH AND DEVELOPMENT","REVENUE PLATFORM"}
						};
		return data;
	}
	@DataProvider (name="testEngineerData")
	Object[][] testEngineerData(){
		Object data [][] = {	{"RESEARCH AND DEVELOPMENT","TEST ENGINEERING","SDET Engineer","Senior Performance Testing Engineer","Senior SDET Engineer"}
						};
		return data;
	}
	@DataProvider (name="SDETData")
	Object[][] testSDETData(){
		Object data [][] = {	{"SDET Engineer","Preferred Qualifications","Minimum Qualifications","Success!"}
						};
		return data;
	}
}

