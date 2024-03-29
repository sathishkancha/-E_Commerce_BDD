package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.de.Aber;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomer_By_Email;
import pageObjects.SearchCustomer_By_Name;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

public class steps extends BaseClass{
	@SuppressWarnings("static-access")
	@Before
	public void setup() throws IOException
	{
		config= new Properties();
		FileInputStream configPropfile = new FileInputStream("config.properties");
		config.load(configPropfile);
		Logger=Logger.getLogger("NOP_E_Commerce");
		PropertyConfigurator.configure("log4j.properties");
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//drivers//chromedriver.exe");
		driver=new ChromeDriver();
		String br=config.getProperty("browser"); //getting the browser name from config.properties file
		
		//Launching browser
		if (br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",config.getProperty("firefoxpath"));
			driver = new FirefoxDriver();
		}

		else if (br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",config.getProperty("chromepath"));
			driver = new ChromeDriver();
		}
		
		else if (br.equals("edge")) {
			System.setProperty("webdriver.edge.driver",config.getProperty("edgepath"));
			driver = new EdgeDriver();
			
		}
	Logger.info("-----launching browser----");
	}

	//login testcase steps
	@Given("user launches the chrome Browser")
	public void user_launches_the_chrome_Browser() {
		
		
		lp=new LoginPage(driver);
		

	}

	@When("User opens URL {string}")
	public void user_opens_URL(String url) {
		Logger.info("--opening UrL ---");
		driver.get(url);
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_Email_as_and_Password_as(String username, String password) {
		Logger.info("--Entering the Login details ---");
		lp.setUserName(username);
		lp.setPassword(password);
	}

	@When("Click on Login")
	public void click_on_Login() {
		Logger.info("--Login button clicked ---");
		lp.clickLogin();

	}

	@Then("Page Title should be {string}")
	public void page_Title_should_be(String text) {
		if(driver.getPageSource().contains("Login was unsuccessful."))
		{	
			Logger.warn("--Login failed---");
			driver.quit();
			Assert.assertTrue(false);
		}
		else {
			Logger.info("--Login successfully verified");
			Assert.assertEquals(text,driver.getTitle());
		}
		
	}

	@When("User Click on Log out Link")
	public void user_Click_on_Log_out_Link() throws InterruptedException {
		Thread.sleep(3000);
		Logger.info("--Logout ---");
		lp.clickLogout();
		Thread.sleep(3000);
	}

	@Then("close browser")
	public void close_browser() {
		Logger.info("--closing browser---");
		driver.quit();

	}
	
	//Add customer steps 



	@Then("User can view Dashboad")
	public void user_can_view_Dashboad() {
		addCust=new AddCustomerPage(driver);
		Assert.assertEquals("Dashboard / nopCommerce administration",addCust.getPageTitle());
	}

	@When("User click on customers Menu")
	public void user_click_on_customers_Menu() throws InterruptedException {
		Thread.sleep(3000);
		addCust.clickOnCustomersMenu();

	}

	@When("click on customers Menu Item")
	public void click_on_customers_Menu_Item() throws InterruptedException {
		Thread.sleep(3000);
		addCust.clickOnCustomersMenuItem();

	}

	@When("click on Add new button")
	public void click_on_Add_new_button() throws InterruptedException {
		addCust.clickOnAddnew();
		Thread.sleep(3000);

	}

	@Then("User can view Add new customer page")
	public void user_can_view_Add_new_customer_page() {
Assert.assertEquals("Add a new customer / nopCommerce administration",addCust.getPageTitle());


	}

	@When("User enter customer info")
	public void user_enter_customer_info() throws InterruptedException {
		String email=RandomString()+"@gmail.com";
		addCust.setEmail(email);
		addCust.setPassword("test123");
		// Registered - default
				// The customer cannot be in both 'Guests' and 'Registered' customer roles
				// Add the customer to 'Guests' or 'Registered' customer role
				addCust.setCustomerRoles("Guest");
				Thread.sleep(3000);
				addCust.setManagerOfVendor("Vendor 2");
				addCust.setGender("Male");
				addCust.setFirstName("Abhi");
				addCust.setLastName("G");
				addCust.setDob("1/02/2000"); // Format: D/MM/YYY
				addCust.setCompanyName("Fast_QA");
				addCust.setAdminContent("This is under test-----");
	}

	@When("click on Save button")
	public void click_on_Save_button() throws InterruptedException {
		addCust.clickOnSave();
		 Thread.sleep(2000);

	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String string) {
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("The new customer has been added successfully"));
	}
	//search cusomter by email 
	@When("Enter customer EMail")
	public void enter_customer_EMail() {
		se=new SearchCustomer_By_Email(driver);
		se.setEmail("kiyjcycyhjc676008@gmail.com");
	}

	@When("Click on search button")
	public void click_on_search_button() throws InterruptedException {
		se.clickSearch();
		Thread.sleep(30);
	}

	@Then("User should found Email in the Search table")
	public void user_should_found_Email_in_the_Search_table() {
	   boolean status=se.searchCustomerByEmail("kiyjcycyhjc676008@gmail.com");
	 Assert.assertEquals(true,status);
	}
	
	//sear customer by last and first name
	@When("Enter customer FirstName")
	public void enter_customer_FirstName() {
		sn=new SearchCustomer_By_Name(driver);
		sn.setFirstName("Virat");
		
	}

	@When("Enter customer LastName")
	public void enter_customer_LastName() {
		sn.setLastName("Kohli");
		se=new SearchCustomer_By_Email(driver);
		se.clickSearch();
	}

	@Then("User should found Name in the Search table")
	public void user_should_found_Name_in_the_Search_table() {
		se=new SearchCustomer_By_Email(driver);
		 boolean status=sn.searchCustomerByName("Virat Kohli");
		 Assert.assertEquals(true,status);
	}
	@After
	public void tearDown() {
		driver.quit();
	}


}
