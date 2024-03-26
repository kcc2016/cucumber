package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "username")
	private WebElement usernameTextField;

	@FindBy(name = "password")
	private WebElement passwordTextField;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement loginButton;

	public void enterUsername(String username) {
		usernameTextField.sendKeys(username);
	}

	public void enterPassword(String password) {
		passwordTextField.sendKeys(password);
	}

	public void clickOnLoginButton() {
		loginButton.click();
	}
}
