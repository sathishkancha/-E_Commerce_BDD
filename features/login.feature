Feature: Login

@regression
Scenario: Successful Login with valid credentials
		Given user launches the chrome Browser
		When User opens URL "https://admin-demo.nopcommerce.com/login"
		And User enters Email as "admin@yourstore.com" and Password as "admin"
		And Click on Login
		Then Page Title should be "Dashboard / nopCommerce administration"
		When User Click on Log out Link
		Then Page Title should be "Your store. Login"
		And close browser
		
		
Scenario Outline: Login Data Driven
		Given user launches the chrome Browser
		When User opens URL "https://admin-demo.nopcommerce.com/login"
		And User enters Email as "<Email>" and Password as "<Password>"
		And Click on Login
		Then Page Title should be "Dashboard / nopCommerce administration"
		When User Click on Log out Link
		Then Page Title should be "Your store. Login"
		And close browser
		
		Examples: 
			| Email | Password |
			| admin@yourstore.com | admin |
			| admin@yourstore.com | admin123 |
		