package testCases;

import testBase.BaseClass;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC_002_LoginTest extends BaseClass{
	
	//Pre-requisite: Manually create an account, so u can use the same email and pswd for login
   @Test(groups= {"Sanity","Master"})
	public void test_Login() {
	   logger.info("*** Starting TC_002_LoginTest ***");
	try {
	HomePage hp=new HomePage(driver);
    hp.clickMyAccount();
    logger.info("Clicked on MyAccount link");
    hp.clickLogin();
    logger.info("Clicked on Login link");
    
    LoginPage lp=new LoginPage(driver);
    logger.info("Providing login details");
    lp.setEmail(rb.getString("email"));  //valid email, get it from config.properties
    lp.setPswd(rb.getString("password")); //valid password, get it from config.properties
    lp.clickLogin();
    logger.info("Clicked on Login button");
    
    MyAccountPage ap=new MyAccountPage(driver);
    boolean head_status=ap.headingDisplay();
    Assert.assertEquals(head_status, true, "Invalid login data");
	   }

   catch(Exception e) 
	   {
	   Assert.fail();
       }
   logger.info("*** Finished TC_002_LoginTest ***");
   }
  
}
	

