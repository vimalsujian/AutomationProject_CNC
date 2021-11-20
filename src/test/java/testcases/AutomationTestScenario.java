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
		homePage.ScrollDown("7000");
		homePage.VerifySecondArticle();
		homePage.ScrollUp("-6000");
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
