package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
	
	public HomePage homepage;
	private WebDriver driver = null;
	
	@FindBy(id="selectProductSort")
	public WebElement dropdown;
		
	public MainPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver,this);
		}
	
	
	public void dropdown() {
		try {
			WebElement dropdownValue=driver.findElement(By.xpath("//*[@id='uniform-selectProductSort']"));
			if(dropdownValue.isDisplayed()==true) {
				WebdriverWaitAndClick(dropdownValue);
				Select value=new Select(dropdown);
				//value.selectByIndex(6);
				//value.selectByVisibleText("Reference: Lowest first");
				value.selectByValue("Product Name: A to Z");
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	public void WebdriverWaitAndClick(WebElement value) {
		WebDriverWait wait =new WebDriverWait(driver,30);
		WebElement element=wait.until(ExpectedConditions.elementToBeClickable(value));
		}
	
}
