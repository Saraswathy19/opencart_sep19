package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.AccountRegistrationPage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass{

 //need to import HomePage as it is present in another package
	@Test(groups= {"Regression", "Master"})
	void test_account_Registration() throws InterruptedException {
		logger.debug("application logs....");
		logger.info("*** Starting TC_001_AccountRegistrationTest ***");
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount link");
		hp.clickRegister();
		logger.info("Clicked on Register link");
		
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		logger.info("Providing customer data");
		regpage.setFirstName(randomeString());
		regpage.setLastName(randomeString());
		regpage.setEmail(randomeString()+"@gmail.com"); //generate random email everytime, as email is unique.
		//If u hardcode, then you will have to do it everytime before running
		regpage.setPhoneNo(randomeNumber());
		String pswd=randomAlphaNumeric();
		regpage.setPassword(pswd);
		regpage.setConfirmPassword(pswd);
		regpage.checkTerms();
		regpage.clickSub();
		logger.info("Clicked on Continue");
		String account_successmsg=regpage.msgDisplay();
		logger.info("Validating expected msg");
		Assert.assertEquals(account_successmsg, "Your Account Has Been Created!", "test failed");
	    }
		catch(Exception e)
		{
			logger.error("test failed");
			Assert.fail();
			e.getMessage();
		}
		logger.info("*** Finished TC_001_AccountRegistrationTest ***");
	}
}
	
