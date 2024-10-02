package tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestNavigation extends BaseTest {
	
	@Test (priority=1)
	public void testNavigateToHomePage() {
		//Verify the Title of Entrata HomePage
		String title = driver.getTitle();
		Assert.assertTrue(title.contains("Entrata"), "Entrata Home Page title is not correct");
	}
	
	@Test (priority=2, dependsOnMethods = {"testNavigateToHomePage"})
	public void testNavigationToStudentPage() {
		//Finding the Solution DropDown and hovring on it
		WebElement solutionsLink = driver.findElement(By.cssSelector("#w-dropdown-toggle-5"));
		Actions moveOnSoultionDropDown = new Actions(driver);
		moveOnSoultionDropDown.moveToElement(solutionsLink).build().perform();

		//Selecting Student option from DropDown and click on it
		//Here not used Select Class Because it not contains Select tag it having div tag
		WebElement student = driver.findElement(By.linkText("Student"));
		student.click();
		
		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("/student"),"Student Page URl not contain '/student'");
	}
	@Test (priority=3, dependsOnMethods = {"testNavigateToHomePage"})
	public void testNavigateToSummitPage() {
		//Finding the Summit Page Button
		WebElement Summit = driver.findElement(By.linkText("Summit"));
		Summit.click();
		
		//Switching to child Window i.e.Summit Page
		windowHandling();	
		
		//Verifying We are on Summit Page and Summit Logo displaying or not
		String currentUrl = driver.getCurrentUrl();
		Assert.assertTrue(currentUrl.contains("/summit"),"Student Page URl not contain '/summit'");
		driver.findElement(By.cssSelector(".summit-landing-logo")).isDisplayed();
		
	}
	@Test (priority=4, dependsOnMethods = {"testNavigateToHomePage"})
	public void testNavigateToAccountingPage() {
		//Finding Accounting Button, Scrolling to it and click on it
		WebElement accounting = driver.findElement(By.id("w-node-_8dd0d511-faef-57fa-5e27-00f24bd45607-47af4adb"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", accounting);
		accounting.click();
		
		//Validate The title is displaying or not
		String expectedTitle = "Entrata® Accounting™";
		String actualTitle = driver.findElement(By.xpath("//*[@class='main-section projects']//h1")).getText(); 
		Assert.assertEquals(actualTitle, expectedTitle);
		
	}
}
