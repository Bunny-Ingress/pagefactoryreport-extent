package testcases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

import extendreport.extentreport;
import pagefactory.homepage;
import readexcel.readexcelmethod;
import pagefactory.Login;


public class callingmethod {

	 WebDriver driver;
	 extentreport extRpt = new extentreport();

	 Login objLogin;

	    homepage objHomePage;
	    
	    readexcelmethod excel = new readexcelmethod();

	    @BeforeTest

	    public void setup() throws IOException{

	        System.setProperty("webdriver.chrome.driver","C:\\Users\\Prabhani\\Downloads\\Selinium\\chrome\\chromedriver.exe");
	        driver = new ChromeDriver();
	       
	        
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        
	        driver.get(excel.readExcel(1,0, ".\\Data\\Book1.xlsx","Sheet1"));
	        extRpt.setUpReport();
	      

	    }

	    /**

	     * This test go to http://demo.guru99.com/V4/

	     * Verify login page title as guru99 bank

	     * Login to application

	     * Verify the home page using Dashboard message
	     * @throws IOException 

	     */

	    @org.testng.annotations.Test(priority=0)

	    public void test_Home_Page_Appear_Correct() throws IOException{

	        //Create Login Page object
	    	 extRpt.startTestCase("User Login");
	    objLogin = new Login(driver);

	    //Verify login page title

	    String loginPageTitle = objLogin.getLoginTitle();

	   // Assert.assertTrue(loginPageTitle.toLowerCase().contains(excel.readExcel(1,3, ".\\Data\\Book1.xlsx","Sheet1")));

	    //login to application

	    objLogin.loginToGuru99(excel.readExcel(1,1, ".\\Data\\Book1.xlsx","Sheet1"), excel.readExcel(1,2, ".\\Data\\Book1.xlsx","Sheet1"));
	    
	    extRpt.logEvents("Logged Successfully");
	    // go the next page

	    objHomePage = new homepage(driver);

	    //Verify home page

	    //Assert.assertTrue(objHomePage.getHomePageDashboardUserName().toLowerCase().contains("Manger Id : mngr136913"));
	    driver.close();
	    extRpt.createFinalReport();
	    }

	
}
