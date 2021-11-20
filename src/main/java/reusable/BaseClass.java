package reusable;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass extends ReadConfiguration {
	public WebDriver driver;
		ReadConfiguration readfile=new ReadConfiguration();
			
				
		@BeforeClass
		public void	 setup(ITestContext context){			
			browser(readfile.getBrowserName());
		}
		
		@AfterClass
		public void tearDown(){
			driver.quit();
		}	
					
		public WebDriver browser(String browserName){
			try {
				if(browserName.equals("chrome")){
					WebDriverManager.chromiumdriver().setup();
					driver= new ChromeDriver();				
				}
				else if(browserName.equals("Firefox")) {
					WebDriverManager.firefoxdriver().setup();
					driver= new FirefoxDriver();		
				}
				String URL=readfile.getURL();			
				driver.navigate().to(URL);
				driver.manage().window().maximize();
				//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			} catch (Exception e) {
				
				System.out.println(e.getMessage());
			}
			return driver;
		}
		
		

}
