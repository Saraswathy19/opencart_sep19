package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC_003_LoginDDT extends BaseClass{

	//If the dataProvider is created in the same class, then this is enough @Test(dataProvider="LoginData") 
	//Since DataProviders class is in different class and package, DataProviders is the className . It should be className.class and then import it
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class)
	public void test_loginDDT(String email, String pswd, String exp) {
		logger.info("*** Starting TC_003_LoginDDT ***");
		try {
		HomePage hp=new HomePage(driver);
	    hp.clickMyAccount();
	    hp.clickLogin();
	    
	    
	    LoginPage lp=new LoginPage(driver);
	    lp.setEmail(email);  //valid email, get it from config.properties
	    lp.setPswd(pswd); //valid password, get it from config.properties
	    lp.clickLogin();
	    
	    
	    MyAccountPage ap=new MyAccountPage(driver);
	    boolean head_status=ap.headingDisplay();
	    
	    if(exp.equals("Valid"))
	    {
	    	if(head_status==true)
	    	{
	    		Assert.assertTrue(true);  //Test case is passed
	    		ap.clicklogout();
	    	}
	    	else
	    	{
	    		Assert.assertTrue(false);
	    	}
	    }
	    
	    if(exp.equals("Invalid"))
	    {
	    	if(head_status==true)
	    	{
	    		Assert.assertTrue(false);
	    		ap.clicklogout();
	    	}
	    	else
	    	{
	    		Assert.assertTrue(true); //Invalid data, exp--to fail, so test case is pass here
	    	}
	    }
		}
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("*** Finished TC_003_LoginDDT ***");
	    
	}
}
