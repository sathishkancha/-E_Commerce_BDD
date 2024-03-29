package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitHelper;

public class SearchCustomer_By_Email {
	public WebDriver ldriver;
	WaitHelper waitHelper;
	public SearchCustomer_By_Email(WebDriver ldriver)
	{
		this.ldriver=ldriver;
		PageFactory.initElements(ldriver, this);
		waitHelper=new WaitHelper(ldriver);
	}
	@FindBy(how = How.ID, using = "SearchEmail")
	@CacheLookup
	WebElement txtEmail;



	@FindBy(how = How.ID, using = "search-customers")
	@CacheLookup
	WebElement btnSearch;
	@FindBy(how = How.XPATH, using = "//table[@class='table table-bordered table-hover table-striped dataTable no-footer']")
	@CacheLookup
	WebElement table;
	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody/tr")
	@CacheLookup
	List<WebElement> tableRows;
	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody/tr/td")
	@CacheLookup
	List<WebElement> tableColumns;

	public void setEmail(String email) {
		waitHelper.WaitForElement(txtEmail, 30);
		txtEmail.clear();
		txtEmail.sendKeys(email);
	}

	

	public void clickSearch() {
		waitHelper.WaitForElement(btnSearch, 30);
		btnSearch.click();

	}
	
	public int getNoOfRows() {
		return (tableRows.size());
	}

	public int getNoOfColumns() {
		return (tableColumns.size());
	}
	
	public boolean searchCustomerByEmail(String email) {
		boolean flag = false;

		for (int i = 1; i <= getNoOfRows(); i++) {
			String emailid = table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr[" + i + "]/td[2]"))
					.getText();

					
			
			System.out.println(emailid);

			if (emailid.equals(email)) {
				flag = true;
				break;
			}
		}

		return flag;

	}

}
