package testcases;

import org.testng.annotations.Test;

import pages.HomePage;
import reusable.BaseClass;

public class AutomationTestScenario extends BaseClass {

	@Test
	public void Scenario_TC01() throws InterruptedException{
		
		HomePage homePage = new HomePage(driver);
		homePage.VerifyTitle();
		homePage.ValidateArticle();
		homePage.ScrollDown();
		homePage.VerifySecondArticle();
		homePage.ScrollUp();
	}
	
	@Test
	public void Scenario_TC02() throws InterruptedException{
		
		HomePage homePage = new HomePage(driver);
		homePage.ValidatingDropDownPage("Top Stories","Singapore");
	
	}
	
	@Test
	public void Scenario_TC03() throws InterruptedException{
		
		HomePage homePage = new HomePage(driver);		
		homePage.ValidatingDropDownPage("Weather","");
		homePage.ValidateWeather("kuala lumpur");

		 
	}
}
