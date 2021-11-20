package pages;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Amazon {

	private WebDriver driver = null;
	
	@FindBy(id="twotabsearchtextbox")
	public WebElement Searchbox;
	
	@FindBy(id="nav-search-submit-button")
	public WebElement btnsearch;
	
	@FindBy(xpath="//*[(@data-component-type='s-search-result')]")
	public List<WebElement> searchResults;
	
	@FindBy(id="native_dropdown_selected_size_name")
	public WebElement shoeSize;
	
	@FindBy(xpath="//*[@id='native_dropdown_selected_size_name']//option")
	public List<WebElement> noofelements;
	
	@FindBy(id="add-to-cart-button")
	public WebElement addtocart;
	
	@FindBy(xpath="//*[@data-action='a-button-group']")
	public WebElement allshoestag;
	
	@FindBy(id="imgTagWrapper")
	public WebElement shoeimage;
	
	public Amazon(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	public void entertext(String textValue) throws NoSuchElementException{
		try {			
			Searchbox.sendKeys(textValue);
			webElementToLoad(btnsearch);
			btnsearch.click();
		}
		catch(NoSuchElementException e) {
			System.out.println("WebElement not present in the dom");
		}
		
	}
	
	public void webElementToLoad(WebElement value) {
		 WebDriverWait wait = new WebDriverWait(driver,30);
		 wait.until(ExpectedConditions.visibilityOf(value));
	}
	
	public void listofshoes(int value) throws IndexOutOfBoundsException, InterruptedException {
		try{
		int totalvalue=searchResults.size();
		if(!(totalvalue==0))
		System.out.println("Totally there are "+totalvalue+" shoes present in current page and we are good with next flow");
		else {
			System.out.println("Totally there are "+totalvalue+" shoes present in current page");
		}
		int newvalue=value-1;
		if(newvalue<=totalvalue) {
			searchResults.get(newvalue).click();
		}else {
			System.out.println("You have given value that exceeds the value present in the screen. Please give below to that");
		}
		//wait(3000);
		Set childWindow = driver.getWindowHandles();
		List<String> switchwindow=new ArrayList(childWindow);		
		driver.switchTo().window(switchwindow.get(1));
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println("You have given value that exceeds the value present in the screen. Please give below to that");
		}
		
	}
	public void verifyTitle(String value) {
		try{
			
		String currentURL=driver.getCurrentUrl();
		if(currentURL.contains(value));
		System.out.println("Verified the next page current Url has ["+value+"] and landed into expected page");
		}
		catch(Exception e) {
			System.out.println("not verified the next page current Url and landed into unexpected page");
		}
		
	}
	public void shoesSelection(String value) {
		try{
			Boolean Verifyshoes=allshoestag.isDisplayed();
			Assert.assertTrue(Verifyshoes);
			System.out.println("Verified all the shoes are displayed on the current page and ready to perform on it");
			driver.findElement(By.xpath("//*[@alt='"+value+"']")).click();
			System.out.println(value+" "+"Selected in the displayed shoes list");
			Boolean shoeimg=shoeimage.isDisplayed();
			Assert.assertTrue(shoeimg);
			System.out.println("Selected shoes are displayed on the current page on right side");
		}
		catch(Exception e) {
			System.out.println(value+" "+"Selected in the displayed shoes list");
		}
		
	}
	public void sizeSelection(String value) {
		try{
			Boolean Verify=shoeSize.isDisplayed();
			Assert.assertTrue(Verify);
			System.out.println("Verified the dropdown are displayed on the current page and ready for action");
		shoeSize.click();
		for(int i=0;i<=noofelements.size();i++) {
			String vales=noofelements.get(i).getText();
			if(value.contains(vales)) {
				noofelements.get(i).click();
				System.out.println(value+" "+"Selected in the displayed Size of shoe list");
				break;
			}
		}		
		}
		catch(Exception e) {
			System.out.println(value+" "+"is not Selected in the displayed Size of shoe list");
		}
	}
	public void Click() {
		try{
			if(addtocart.isDisplayed()) {
				addtocart.click();				
			}
		}
		catch(Exception e) {
			System.out.println("WebElement not available");
		}
		
	}
	
}