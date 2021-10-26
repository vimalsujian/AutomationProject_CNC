package pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	private WebDriver driver = null;
	
	@FindBy(xpath="//*[starts-with(@class, 'feature-card__heading-link')]")
	public WebElement headlineTitle;
	
	@FindBy(xpath="//*[@class='h1 h1--page-title']")
	public WebElement fulllineTitle;
	
	
	@FindBy(xpath="//*[starts-with(@class,'article__read-full-story-button article__read-full-story-button--')]")
	public WebElement expandbtn;
	
	@FindBy(xpath="//*[starts-with(@class,'all-section-menu main-menu__link')]")
	public WebElement allSectbtn;
	
	public String title;
	
	public HomePage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
			
	}
		
	public void VerifyTitle() {
		try{
			
			if(!headlineTitle.getText().isEmpty()) {
				title=headlineTitle.getText();
				System.out.println("Headline new item Title: "+title);
			}
		}
		catch(Exception e) {
			System.out.println("Headline new item Title is not available");
		}
		
	}
	public void Click(WebElement element) {
		try{
			if(element.isDisplayed()) {
				element.click();				
			}
		}
		catch(Exception e) {
			System.out.println("WebElement not available");
		}
		
	}
	
	
	public void ValidateArticle() {
		try{
			Click(headlineTitle);
			if(title.equals(fulllineTitle.getText()));
			System.out.println("Article Title: "+title+" "+"and Landed successfully in expected article");	
		}
		catch(Exception e) {
			System.out.println("Headline new item Title is not available");
		}
		
	}
	public void ScrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,5000)");
        }
	
	public void ScrollUp() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-5000)");
        }
	public void VerifySecondArticle() throws InterruptedException {
		try{
		Thread.sleep(1000);
		List value=driver.findElements(By.xpath("//*[starts-with(@class,'article__read-full-story-button article__read-full-story-button--')]"));
		Thread.sleep(500);
		int innerValue=value.size();
		driver.findElement(By.xpath("//*[starts-with(@id, 'block-mc-cna-theme-mainpagecontent')]/article["+innerValue+"]/div[@class='article__read-full-story-wrapper hidden']//a")).click();
		String secondPageTitle=driver.getTitle();
		String secondArticleTitle=driver.findElement(By.xpath("//*[starts-with(@id, 'block-mc-cna-theme-mainpagecontent')]/article["+innerValue+"]//*[@class='h1 h1--page-title']")).getText();
			if(secondPageTitle.contains(secondArticleTitle)) {
				System.out.println("2nd Article title: "+secondPageTitle+"and expanded the respective story");
			}
		}
		catch(Exception e) {
			System.out.println("2nd Article title not display as expected");
		}
	}
	
	public void ValidatingDropDownPage(String firstValue, String secondValue) throws InterruptedException {
		try {
			allSectbtn.click();		
			List value=driver.findElements(By.xpath("//*[@id='block-mc-cna-theme-allsectionmenumodal']"));
			Actions action = new Actions(driver);
			WebElement elements= driver.findElement(By.xpath("(//*[@id='block-mc-cna-theme-allsectionmenumodal'])["+value.size()+"]//a[contains(text(),'"+firstValue+"')]"));
			action.moveToElement(elements).build().perform();			
			if(firstValue.contains("Top Stories")) {
				driver.findElement(By.xpath("(//*[@id='block-mc-cna-theme-allsectionmenumodal'])["+value.size()+"]//a[contains(text(),'"+firstValue+"')]//..//a[contains(text(),'"+secondValue+"')]")).click();
				String currentUrl=getUrl().substring(32,35);
				if(currentUrl.contains("international") || secondValue.toLowerCase().contains(currentUrl)) {
					System.out.println("Navigate to:"+secondValue+" Page");
				}
			}
			else if(firstValue.contains("Weather") || firstValue.contains("CNA Insider") || firstValue.contains("Latest News") || firstValue.contains("Discover")) {
				driver.findElement(By.xpath("(//*[@id='block-mc-cna-theme-allsectionmenumodal'])["+value.size()+"]//a[contains(text(),'"+firstValue+"')]")).click();
				String currentUrl=getUrl().substring(32,35);
				if(firstValue.toLowerCase().contains(currentUrl)) {
					System.out.println("Navigate to:"+firstValue+" Page");
				}
			}
			else {
				WebElement elements1= driver.findElement(By.xpath("(//*[@id='block-mc-cna-theme-allsectionmenumodal'])["+value.size()+"]//a[contains(text(),'"+firstValue+"')]"));
				action.moveToElement(elements1).build().perform();			
				driver.findElement(By.xpath("(//*[@id='block-mc-cna-theme-allsectionmenumodal'])["+value.size()+"]//a[contains(text(),'"+firstValue+"')]//..//a[contains(text(),'"+secondValue+"')]")).click();
				System.out.println("Navigate to:"+secondValue+" Page");
				}
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		}
	
	public String getUrl() {
		String Url= driver.getCurrentUrl();
		return Url;
	}
	
	
	public void ValidateWeather(String cityName) {
		try {
			List value=driver.findElements(By.xpath("//*[@class='asia-lists tabular-list']//li"));
			for(int i=1;i<value.size();i++) {
				String city=driver.findElement(By.xpath("(//*[@class='asia-lists tabular-list']//li//div[@class='tabular-list__view--city'])["+i+"]")).getText().toString();  
				String condition=driver.findElement(By.xpath("(//*[@class='asia-lists tabular-list']//li//div[@class='tabular-list__view--condition'])["+i+"]")).getText().toString();   
				String maxtemp=driver.findElement(By.xpath("(//*[@class='asia-lists tabular-list']//li//*[@class='tabular-list__view--temp--max'])["+i+"]")).getText().toString();      
				String mintemp=driver.findElement(By.xpath("(//*[@class='asia-lists tabular-list']//li//*[@class='tabular-list__view--temp--min'])["+i+"]")).getText().toString();   
				if(city.equalsIgnoreCase(cityName)) {
					if(!condition.isEmpty() && !maxtemp.isEmpty() && !mintemp.isEmpty());
						System.out.println("CityName: "+cityName+"and condition: "+condition+"and MaximumTemparture: "+maxtemp+"and MinimunTemparture: "+mintemp);
					break;
				}
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
}
