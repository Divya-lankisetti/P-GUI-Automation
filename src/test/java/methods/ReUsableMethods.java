package methods;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.PropertiesFileManager;
import io.github.bonigarcia.wdm.WebDriverManager;

public final class ReUsableMethods {

	static WebDriver driver;
	static JavascriptExecutor jsExecutor;

	@SuppressWarnings("deprecation")
	public static WebDriver initializeChromeDriver() {
		ChromeOptions chromeOptions = new ChromeOptions();
		//chromeOptions.addArguments("--headless");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(chromeOptions);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		return driver;
	}

	public static void closeUpChromeDriver() {
		driver.quit();
	}

	public static void openApplicationURL() {
		PropertiesFileManager.assignFileName("config.properties");
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println("Currently opening application:- "+
				PropertiesFileManager.getLocator("appURL"));
		System.out.println("------------------------------------------------------------------------------------\n");
		
		driver.get(PropertiesFileManager.getLocator("appURL"));
	}

	public static List<WebElement> getElements(String key)
	{
		String locator = PropertiesFileManager.getLocator(key);
		return findElements(locator);

	}

	public static List<WebElement> findElements(String locator) {
		String[] locatorParts = locator.split(":", 2);
		String locatorType = locatorParts[0].trim();
		String locatorValue = locatorParts[1].trim();

		
		switch (locatorType.toLowerCase()) {
		case "id":
			System.out.println("ID - "+locatorType +" - "+locatorValue);
			By byID=By.id(locatorValue);
			waitForElementVisible(driver,byID,Duration.ofSeconds(20));
			return driver.findElements(byID);
		case "name":
			System.out.println("NAME - "+locatorType +" - "+locatorValue);
			By byName=By.name(locatorValue);
			waitForElementVisible(driver,byName,Duration.ofSeconds(20));
			return driver.findElements(byName);
		case "cssselector":
			System.out.println("CSS_Selector - "+locatorType +" - "+locatorValue);
			By byCssSelector=By.cssSelector(locatorValue);
			waitForElementVisible(driver,byCssSelector,Duration.ofSeconds(20));
			return driver.findElements(byCssSelector);
		case "xpath":
			System.out.println("XPATH - "+locatorType +" - "+locatorValue);
			By byXpath=By.xpath(locatorValue);
			if(!locatorValue.contains("radio"))
				waitForElementVisible(driver,byXpath,Duration.ofSeconds(20));
			return driver.findElements(byXpath);
			// Add more cases for other locator types as needed
		default:
			throw new IllegalArgumentException("Unsupported locator type: " + locatorType);
		}
	}



	public static WebElement getElement(String key) {
		System.out.println("Key - "+key);
		String locator = PropertiesFileManager.getLocator(key);
		System.out.println("locator - "+locator);
		// logger.info("Reading "+key+" WebElement Locator from Properties File !");
		return findElement(locator);
	}

	/*public static void main(String[] args) {
		getElement("Emailfield");

	}*/

	public static WebElement waitForElementVisible(WebDriver driver, By locator, Duration timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	//ExpectedConditions.elementToBeClickable(locator))

	public static void captureScreenshot(WebDriver driver, String screenShotPath) {
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		 
		
		LocalDateTime localDateTime=LocalDateTime.now();
		String nameParsing=localDateTime.toString();
		nameParsing=nameParsing.replace(":", "_");
		 
		try {             
			FileUtils.copyFile(screenshotFile, new File(screenShotPath + nameParsing+".jpg"));         
		} catch (IOException e) {             
			System.err.println("Failed to save screenshot: " + e.getMessage());        
		}

	}

	public static WebElement byToWebelement(By  by){
		return driver.findElement(by);
	}
	
	public static WebElement findElement(String locator) {
		String[] locatorParts = locator.split(":", 2);
		String locatorType = locatorParts[0].trim();
		String locatorValue = locatorParts[1].trim();

		switch (locatorType.toLowerCase()) {
		case "id":
			System.out.println("ID - "+locatorType +" - "+locatorValue);
			By byID=By.id(locatorValue);
			waitForElementVisible(driver,byID,Duration.ofSeconds(20));
			return driver.findElement(byID);
		case "name":
			System.out.println("NAME - "+locatorType +" - "+locatorValue);
			By byName=By.name(locatorValue);
			waitForElementVisible(driver,byName,Duration.ofSeconds(20));
			return driver.findElement(byName);
		case "cssselector":
			System.out.println("CSS_Selector - "+locatorType +" - "+locatorValue);
			By byCssSelector=By.cssSelector(locatorValue);
			waitForElementVisible(driver,byCssSelector,Duration.ofSeconds(20));
			return driver.findElement(byCssSelector);
		case "xpath":
			System.out.println("XPATH - "+locatorType +" - "+locatorValue);
			By byXpath=By.xpath(locatorValue);
			if(!locatorValue.contains("radio"))
				waitForElementVisible(driver,byXpath,Duration.ofSeconds(20));
			return driver.findElement(byXpath);
			// Add more cases for other locator types as needed
		default:
			throw new IllegalArgumentException("Unsupported locator type: " + locatorType);
		}
	}
	
	public static void viewButtonCheck(String planName, String buttonToClick) {
		/* planName:-
		 * TSP - Trade Spend Plan
		 * MRSP - MR Spend Plan ( P&G MR, E-Comm & Omni Trade Spend Plan )
		 * 
		 * buttonToClick:-
		 * viewBtn -- view button of the plan Name
		 * build -- build button under the plan name
		 */
		System.out.println("Execution under - viewButtonCheck Method with Inputs- "+planName+" And "+buttonToClick);

		if(planName.equalsIgnoreCase("TSP")) {
			if(buttonToClick.equalsIgnoreCase("view"))
				ReUsableMethods.getElement("viewbtn").click();
			else if(buttonToClick.equalsIgnoreCase("build"))
				System.out.println("Note - Trying for build click check");
		}

	}

	public static String doAsPerPassingParameters(WebElement element,String actionToDo,String dataToEnter) {
		String outputOfThisMethodCall="";
		try {
			if(actionToDo.equalsIgnoreCase("click")) {
				element.click();
				outputOfThisMethodCall=actionToDo+" Done using driver instance Sucessfully";
			}else if(actionToDo.equalsIgnoreCase("sendkeys")) {
				element.sendKeys(dataToEnter);
				outputOfThisMethodCall="Value - "+dataToEnter+" instered using driver instance Sucessfully";
			} 		
		} catch (Exception e) {
			jsExecutor = (JavascriptExecutor) driver;
			if(actionToDo.equalsIgnoreCase("click")) {
				jsExecutor.executeScript("arguments[0].click();", element);
				outputOfThisMethodCall=actionToDo+" Done using JSExecutor instance Sucessfully";
			}else if(actionToDo.equalsIgnoreCase("sendkeys")) {
				// Execute JavaScript code to set the value property of the input element
				jsExecutor.executeScript("arguments[0].value = arguments[1];", element, dataToEnter);
				outputOfThisMethodCall="Value - "+dataToEnter+" instered using JSExecutor instance Sucessfully";
			} 
		}
		return outputOfThisMethodCall;
	}

	private static void scrollToElement(WebDriver driver, WebElement element) {
	
		jsExecutor = (JavascriptExecutor) driver;

		// Execute JavaScript code to scroll to the element
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);

		// You can customize the scrolling behavior based on your needs
	}
}
