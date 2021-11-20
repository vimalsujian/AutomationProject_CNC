package testcases;

import org.testng.annotations.Test;
import reusable.BaseClass;
import pages.Amazon;


public class Amazontestcase extends BaseClass{

	@Test
	public void Scenario_TC01() throws InterruptedException{
		
		Amazon am=new Amazon(driver);
		am.entertext("shoe men sneakers");
		am.listofshoes(1);
		am.verifyTitle("shoe men sneakers");
		am.shoesSelection("Coral Red");
		am.sizeSelection("8 UK");
		am.Click();
		Thread.sleep(3000);
		
	}
	
}
