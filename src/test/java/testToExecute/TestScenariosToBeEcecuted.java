package testToExecute;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import config.PropertiesFileManager;
import methods.ReUsableMethods;

public class TestScenariosToBeEcecuted {

	static List<String> brandNames=new ArrayList<String>();
	static List<String> phasesNames=new ArrayList<String>();
	static int totalPhasesCount=0;
	static WebDriver driver;
	String locator;
	static String screenShotPath="C:\\Program Files\\eclipse-workspace\\P&G\\Screenshots\\";
	 

	//= PropertiesFileManager.getLocator(key);

	@BeforeMethod
	public void initializeTheDriver() {
		driver = ReUsableMethods.initializeChromeDriver();
		try {
			ReUsableMethods.openApplicationURL();	

			PropertiesFileManager.assignFileName("elements.properties");
			locator= PropertiesFileManager.getLocator("EmailInputBox");
			By byOfEmailIDTextBox=By.xpath(locator);
			ReUsableMethods.waitForElementVisible(driver, byOfEmailIDTextBox, Duration.ofSeconds(10)); 
			driver.findElement(byOfEmailIDTextBox).sendKeys("divya.mekala@dsilo.io");

			locator= PropertiesFileManager.getLocator("PasswordInputBox");
			By byOfPasswordTextBox=By.id(locator);
			ReUsableMethods.waitForElementVisible(driver, byOfPasswordTextBox, Duration.ofSeconds(10));
			driver.findElement(byOfPasswordTextBox).sendKeys("mvZEzbBs0z");

			locator= PropertiesFileManager.getLocator("SigInBtn");
			By byOfSignInBtn=By.xpath(locator);
			ReUsableMethods.waitForElementVisible(driver, byOfSignInBtn, Duration.ofSeconds(10));
			driver.findElement(byOfSignInBtn).click();

			locator= PropertiesFileManager.getLocator("viewBtnOnTSP");
			By viewBtnOnTSP=By.xpath(locator);
			ReUsableMethods.waitForElementVisible(driver, viewBtnOnTSP, Duration.ofSeconds(10));
			driver.findElement(viewBtnOnTSP).click();

			System.out.println("\n---------------  Login Sucessful  ---------------");


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterMethod
	public void terminateSession() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
		Thread.sleep(3000);
	}
	@Test(priority=1)
	public void setupPhasesVerification() {
		try {

			System.out.println("\n---------------  Verification of Setup Phases  ---------------");

			By firstPhaseOnSetupHomePage= By.xpath(PropertiesFileManager.getLocator("firstPhaseOnSetupHomePage"));
			ReUsableMethods.waitForElementVisible(driver, firstPhaseOnSetupHomePage, Duration.ofSeconds(10));
			driver.findElement(firstPhaseOnSetupHomePage).click();
			System.out.println("Inside Internal Page of Setup Phases");

			//sleep
			Thread.sleep(3000);

			By BrandsDrpDwnBtn=By.xpath(PropertiesFileManager.getLocator("BrandsDrpDwnBtn"));	
			ReUsableMethods.waitForElementVisible(driver, BrandsDrpDwnBtn, Duration.ofSeconds(10));
			driver.findElement(BrandsDrpDwnBtn).click();
			System.out.println("Click on BRANDS dropdown button Sucessfull !");

			Thread.sleep(3000);
			By getAllBrandName=By.xpath(PropertiesFileManager.getLocator("getAllBrandName"));
			List<WebElement> allBrandsAvailable= driver.findElements(getAllBrandName);
			System.out.println("Total Brands Available in SETUP pahse are - "+allBrandsAvailable.size());
			for(int i=0;i<allBrandsAvailable.size();i++) {
				brandNames.add(allBrandsAvailable.get(i).getText());
			}

			System.out.println("Brands List Updated Sucessfully !\n");

			for(String brand:brandNames) {
				System.out.println("\n------------------- | Current Brand is - "+brand +" | -------------------");
				driver.findElement(By.xpath("//li[text()='"+brand+"']")).click();
				String rootPathOfEachPhaseLocator=PropertiesFileManager.getLocator("listOfSetupPhases");
				phasesNameAvailable(rootPathOfEachPhaseLocator);
				totalPhasesCount=phasesNames.size();

				Thread.sleep(4000);
				driver.navigate().refresh();
				Thread.sleep(4000);
				for(int i=0;i<totalPhasesCount;i++) {
					String currentPhase=phasesNames.get(i);
					//System.out.println("\nCurrent Brand :- "+brand+" Phase is :- "+currentPhase);

					String currentPhaseLocator=rootPathOfEachPhaseLocator+"[text()='"+currentPhase+"']//parent::div";
					//					System.out.println("Updated Locater Based on Current Phase - "+currentPhaseLocator);
					Thread.sleep(1000);
					try {
						driver.findElement(By.xpath(currentPhaseLocator)).click();
						Thread.sleep(2000);	
					} catch (ElementClickInterceptedException e) {
						driver.findElement(By.xpath(currentPhaseLocator)).click();
						Thread.sleep(2000);	
					}
					Thread.sleep(1000);		
					System.out.println("'"+currentPhase+"' phase of Setup Module verified sucessfully !");
				}

				ReUsableMethods.waitForElementVisible(driver, BrandsDrpDwnBtn, Duration.ofSeconds(10));
				driver.findElement(BrandsDrpDwnBtn).click();



			}	 


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(priority=2)
	public void planningPhasesVerification()
	{

		try {

			System.out.println("\n---------------  Verification of Planning Phases  ---------------");

			By firstPhaseOnPlanningHomePage= By.xpath(PropertiesFileManager.getLocator("firstPhaseOnPlanningHomePage"));
			ReUsableMethods.waitForElementVisible(driver, firstPhaseOnPlanningHomePage, Duration.ofSeconds(10));
			driver.findElement(firstPhaseOnPlanningHomePage).click();
			System.out.println("Inside Internal Page of planning Phases");

			//sleep
			Thread.sleep(3000);

			By BrandsDrpDwnBtn=By.xpath(PropertiesFileManager.getLocator("BrandsDrpDwnBtn"));	
			ReUsableMethods.waitForElementVisible(driver, BrandsDrpDwnBtn, Duration.ofSeconds(10));
			driver.findElement(BrandsDrpDwnBtn).click();
			System.out.println("Click on BRANDS dropdown button Sucessfull !");

			Thread.sleep(3000);
			By getAllBrandName=By.xpath(PropertiesFileManager.getLocator("getAllBrandName"));
			List<WebElement> allBrandsAvailable= driver.findElements(getAllBrandName);
			System.out.println("Total Brands Available in planning phase are - "+allBrandsAvailable.size());
			for(int i=0;i<allBrandsAvailable.size();i++) {
				brandNames.add(allBrandsAvailable.get(i).getText());
			}

			System.out.println("Brands List Updated Sucessfully !\n");

			for(String brand:brandNames) {
				System.out.println("\n------------------- | Current Brand is - "+brand +" | -------------------");
				driver.findElement(By.xpath("//li[text()='"+brand+"']")).click();
				String rootPathOfEachPhaseLocator=PropertiesFileManager.getLocator("listOfplanningPhases");
				phasesNameAvailable(rootPathOfEachPhaseLocator);
				totalPhasesCount=phasesNames.size();

				Thread.sleep(4000);
				driver.navigate().refresh();
				Thread.sleep(4000);
				for(int i=0;i<totalPhasesCount;i++) {
					String currentPhase=phasesNames.get(i);
					//System.out.println("\nCurrent Brand :- "+brand+" Phase is :- "+currentPhase);

					String currentPhaseLocator=rootPathOfEachPhaseLocator+"[text()='"+currentPhase+"']//parent::div";
					//System.out.println("Updated Locater Based on Current Phase - "+currentPhaseLocator);
					Thread.sleep(1000);
					try {
						driver.findElement(By.xpath(currentPhaseLocator)).click();
						Thread.sleep(2000);	
					} catch (ElementClickInterceptedException e) {
						driver.findElement(By.xpath(currentPhaseLocator)).click();
						Thread.sleep(2000);	
					}
					Thread.sleep(1000);		
					System.out.println("'"+currentPhase+"' phase of Planning Module verified sucessfully !");
				}

				ReUsableMethods.waitForElementVisible(driver, BrandsDrpDwnBtn, Duration.ofSeconds(10));
				driver.findElement(BrandsDrpDwnBtn).click();



			}	 


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(priority=3)
	public void repportingPhasesVerification() {
 
		screenShotPath=screenShotPath+"ReportingAllPhasesVerification/";
		
		ReUsableMethods.captureScreenshot(driver, screenShotPath);
		try {

			
			System.out.println("\n---------------  Verification of Reporting Phases  ---------------");
			Thread.sleep(3000);
			int TotalBrands=totalBrandsOnHomePage();
			Thread.sleep(5000);
			ReUsableMethods.captureScreenshot(driver, screenShotPath);
			driver.navigate().refresh();
			Thread.sleep(3000);
			for(int i=1;i<=TotalBrands;i++) {
				String reportAllPhasesHomePageEoot=PropertiesFileManager.getLocator("reportAllPhasesHomePage");
				Thread.sleep(3000);
				ReUsableMethods.captureScreenshot(driver, screenShotPath);
				By reportAllPhasesHomePage= By.xpath(PropertiesFileManager.getLocator("reportAllPhasesHomePage"));
				int reportPhasesCount=driver.findElements(reportAllPhasesHomePage).size();
				for(int reptPhase=1;reptPhase<=reportPhasesCount;reptPhase++) {
					Thread.sleep(3000);
					WebElement element= driver.findElement(By.xpath(reportAllPhasesHomePageEoot+"["+reptPhase+"]"));
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
					Thread.sleep(2000);
					ReUsableMethods.captureScreenshot(driver, screenShotPath);
					System.out.println("'"+element.getText()+"' phase of 'Report' phase !");
					element.click();
					Thread.sleep(7000);
					ReUsableMethods.captureScreenshot(driver, screenShotPath);
					driver.navigate().back();
					//bringToHomePage();
					Thread.sleep(3000);
				}

				if(i<TotalBrands)
					brandChanger(i+1);

			}
			//sleep
			Thread.sleep(3000);
		}catch (Exception e) {
			System.out.println("Inside Report phase Catch");
			e.printStackTrace();
		}

	}
	@Test(enabled = false)
	public void configurePhasesVerification() {

		try {

			System.out.println("\n---------------  Verification of Configure Phases  ---------------");

			String ConfigureAllPhasesHomePageRoot=PropertiesFileManager.getLocator("ConfigureAllPhasesHomePage");
			Thread.sleep(3000);
			By configureAllPhasesHomePage= By.xpath(PropertiesFileManager.getLocator("ConfigureAllPhasesHomePage"));
			int configurePhasesCount=driver.findElements(configureAllPhasesHomePage).size();
			for(int cnfgPhase=1;cnfgPhase<=configurePhasesCount;cnfgPhase++) {
				Thread.sleep(3000);
				WebElement element= driver.findElement(By.xpath(ConfigureAllPhasesHomePageRoot+"["+cnfgPhase+"]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
				System.out.println("'"+element.getText()+"' phase of 'Configure' phase !");
				element.click();
				Thread.sleep(3000);
				driver.navigate().back();
				//bringToHomePage();
				Thread.sleep(3000);
			}

			//sleep
			Thread.sleep(3000);
		}catch (Exception e) {
			System.out.println("Inside Configure phase Catch");
			e.printStackTrace();
		}

	}


	public static int totalBrandsOnHomePage() {
		int totalsBramds=0;
		By brandsOpenerBtnOnHomePage=By.xpath(PropertiesFileManager.getLocator("homepageBrandsDropDownOpener"));
		WebElement brandsOpenerbtn= ReUsableMethods.waitForElementVisible(driver, brandsOpenerBtnOnHomePage, Duration.ofSeconds(10));
		brandsOpenerbtn.click();

		By totalBrandOnHomePage=By.xpath(PropertiesFileManager.getLocator("homepageBrandsList"));
		return driver.findElements(totalBrandOnHomePage).size();

	}

	public static void brandChanger(int brandNumber) {
		By brandsOpenerBtnOnHomePage=By.xpath(PropertiesFileManager.getLocator("homepageBrandsDropDownOpener"));
		WebElement brandsOpenerbtn= ReUsableMethods.waitForElementVisible(driver, brandsOpenerBtnOnHomePage, Duration.ofSeconds(10));
		brandsOpenerbtn.click();
		String homepageBrandsList=PropertiesFileManager.getLocator("homepageBrandsList");
		WebElement newBrandNow= driver.findElement(By.xpath(PropertiesFileManager.getLocator("homepageBrandsList")+"["+brandNumber+"]"));
		System.out.println("--------- Brand Name Changed to - "+newBrandNow.getText());
		newBrandNow.click();
	}

	public static void bringToHomePage() {
		try {
			driver.findElement(By.xpath(PropertiesFileManager.getLocator("dashboardIfWrapMenu"))).click();
		} catch (Exception e) {
			driver.findElement(By.xpath(PropertiesFileManager.getLocator("dashboard"))).click();
		}
	}

	public void phasesNameAvailable(String rootPathOfEachPhaseLocator){

		System.out.println(rootPathOfEachPhaseLocator);
		phasesNames.clear();
		List<WebElement> listOfCurrentModulePhases=driver.findElements(By.xpath(rootPathOfEachPhaseLocator));	
		for(WebElement tempElem:listOfCurrentModulePhases) {
			phasesNames.add(tempElem.getText());			
		}

	}
	
	public  void  errorElementisPresent(String welexpath)
	{
		// Check if an error element is present 
	
		if (driver.findElement(By.xpath(welexpath)).isDisplayed())
				{  
			System.out.println("Error Is Present");
			
		}
	
	}
	
	public void samplefunction()
	{
		System.out.println(PropertiesFileManager.getLocator("Error"));
		errorElementisPresent(PropertiesFileManager.getLocator("Error"));
	}
	
	
	
	
}






