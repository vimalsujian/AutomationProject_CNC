package testcases;
import org.testng.annotations.Test;

import pages.MainPage;
import reusable.BaseClass;
public class AutomatingScenario extends BaseClass {

	@Test
	public void Scenario_TC01() throws InterruptedException{
		
		MainPage mainPage = new MainPage(driver);
		mainPage.dropdown();
	}
	
}
