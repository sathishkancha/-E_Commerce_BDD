
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

public class SearchCustomer_By_Name extends SearchCustomer_By_Email{
		public WebDriver ldriver;
		WaitHelper waitHelper;
		SearchCustomer_By_Email se;
		public SearchCustomer_By_Name(WebDriver ldriver)		
		{	super(ldriver);
			this.ldriver=ldriver;
			PageFactory.initElements(ldriver, this);
			waitHelper=new WaitHelper(ldriver);
		}
		@FindBy(how = How.ID, using = "SearchFirstName")
		@CacheLookup
		WebElement txtFirstName;

		@FindBy(how = How.ID, using = "SearchLastName")
		@CacheLookup
		WebElement txtLastName;
		
		public void setFirstName(String fname) {
			waitHelper.WaitForElement(txtFirstName, 30);
			txtFirstName.clear();
			txtFirstName.sendKeys(fname);
		}

		public void setLastName(String lname) {
			waitHelper.WaitForElement(txtFirstName, 30);
			txtLastName.clear();
			txtLastName.sendKeys(lname);
		}
		
		
	
	public boolean searchCustomerByName(String Name) {
		boolean flag = false;

		for (int i = 1; i <= getNoOfRows(); i++) {
			String name = table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr[" + i + "]/td[3]"))
					.getText();

			
			if (Name.equals(name)) {
				flag = true;
				break;
			}
		}

		return flag;

	}

}


