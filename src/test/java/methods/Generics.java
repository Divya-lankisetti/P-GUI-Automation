package methods;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Generics {
	
	WebDriver driver;
	// This is for click Action
	public void clickingOnWebElement(WebElement element, long waitTimeInSeconds) {

		WebDriverWait webWait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeInSeconds));
		WebElement elements = null;

		elements = webWait.until(ExpectedConditions.elementToBeClickable(element));
		elements.click();

	}
	
	//This is for sendkeysAction :: on text filed method
	
	public void sendKeysOnWebElement(WebElement element,String text)
	{
		
		element.click();
		element.clear();
		element.sendKeys(text);
	}
	
	//This is for selecting dropdown 
	
	public void selectByVisisbleText(WebElement element,String text)
	{
		Select select = new Select(element);
		select.selectByVisibleText(text);
		
	}
	
	// accepting alert from UI 
	public void acceptAler(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	
	
	// this method is for mouse hovering from one element to other element 
	public void mouseHoverAndClickElement(WebElement element)
	
	{
		Actions action = new Actions(driver);
		action.moveToElement(element).click().build().perform();
		
	}
	//getwindow handle :: this method is for javascript executor 
	
	public String getCurrentWindowID()
	{
		return driver.getWindowHandle();
	}
	

}
