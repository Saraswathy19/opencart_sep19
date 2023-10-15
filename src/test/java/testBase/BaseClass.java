package testBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger; //logging

public class BaseClass {

	public static WebDriver driver; //Make sure WebDriver is static in BaseClass, we refer same driver instance in ExtentReportUtility.
	public Logger logger;  //U need to import the right package: import org.apache.logging.log4j.Logger;
	public ResourceBundle rb; //import from java.util -->Step4
	
	@BeforeClass(groups= {"Master","Sanity","Regression"})
	@Parameters({"browser"})
	public void setup(String br) { //When we don't specify any access specifier, then the method will be a default access specifier
		
		rb=ResourceBundle.getBundle("config");  //It is used to load config.properties file -->Step4
		logger=LogManager.getLogger(this.getClass()); //logging
		
		//ChromeOptions options=new ChromeOptions();
		//options.setExperimentalOption("excludeSwitches",new String[] {"enable-automation"});
		
		
		//Since dependency selenium-java is 4.12.1, WebDriverManager.chromedriver().setup(); is not needed to be written in the code
		//driver=new ChromeDriver(); is enough. It will itself set the chrome and launch it
		if(br.equals("chrome")) {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		}
		else if(br.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		else {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		driver.manage().deleteAllCookies();
		driver.get(rb.getString("appURL"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
	}
	
	@AfterClass(groups= {"Master","Sanity","Regression"})
	public void tearDown() {
		driver.quit();
	}
	
	
	public String randomeString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5); //5-it will generate random string which will have 5characters. 
		return (generatedString);                         //If we put 10, we will get 10character string
	}

	public String randomeNumber() {
		String generatedString2 = RandomStringUtils.randomNumeric(10);
		return (generatedString2);
	}
	
	public String randomAlphaNumeric() {
		String st = RandomStringUtils.randomAlphabetic(4);
		String num = RandomStringUtils.randomNumeric(3);
		
		return (st+"@"+num);
	}
	
	public String captureScreen(String tname) throws IOException {
		
		//SimpleDateFormat df=new SimpleDateFormat("yyyyMMddhhmmss");  //new SimpleDateFormat is used to create Object. df is just a object reference variable
		//Date dt=new Date();
		//String timeStamp=df.format(dt);
		
		//Combining the above 3statements into one line

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";  //Storing it in screenshots folder

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		return destination;

	}
	
}
