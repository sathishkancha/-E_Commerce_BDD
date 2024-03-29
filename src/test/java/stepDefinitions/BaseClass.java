package stepDefinitions;

import java.util.Properties;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.WebDriver;

import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomer_By_Email;
import pageObjects.SearchCustomer_By_Name;
import org.apache.log4j.Logger;

public class BaseClass {
	public WebDriver driver;
	public LoginPage lp;
	
	public AddCustomerPage addCust;
	public SearchCustomer_By_Email se;
	public SearchCustomer_By_Name sn;
	public static Logger Logger;
	public Properties config;
	
	public static String RandomString() {
		String generatedString1=RandomStringUtils.randomAlphanumeric(5);
		return generatedString1;
	}

}
