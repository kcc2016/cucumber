package stepDefinitions;

import java.io.IOException;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pom.LoginPage;
import utility.ExcelReader;

public class Steps {
	WebDriver driver;
	ExcelReader reader = new ExcelReader();
	LoginPage lp;
	Logger logger = LogManager.getLogger();

	@Given("the user is at OrangeHRM login page")
	public void the_user_is_at_orange_hrm_login_page() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		logger.info("login page displayed");
	}

	@When("the user enters credentials in row number {string} of excel file")
	public void the_user_enters_credentials_in_row_number_of_excel_file(String row)
			throws EncryptedDocumentException, IOException {
		lp = new LoginPage(driver);

		int rowNum = Integer.parseInt(row) - 1;
		String username = reader.readDataFromExcel("Login", rowNum, 0);
		String password = reader.readDataFromExcel("Login", rowNum, 1);

		lp.enterUsername(username);
		lp.enterPassword(password);
		logger.info("user has entered username and password");
	}

	@When("clicks on login button")
	public void clicks_on_login_button() throws InterruptedException {
		lp = new LoginPage(driver);
		lp.clickOnLoginButton();
		logger.info("user has clicked on login button");
		Thread.sleep(2000);
	}

	@Then("home page is displayed")
	public void home_page_is_displayed() throws InterruptedException {
		Assert.assertTrue(driver.findElement(By.xpath("//h6[text()='Dashboard']")).isDisplayed());
		logger.info("Test Passed");
		Thread.sleep(2000);
		driver.quit();
	}

	@Then("login is unsuccessful")
	public void login_is_unsuccessful() throws InterruptedException {
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Invalid credentials']")).isDisplayed());
		logger.info("Test Passed");
		Thread.sleep(2000);
		driver.quit();
	}

}
