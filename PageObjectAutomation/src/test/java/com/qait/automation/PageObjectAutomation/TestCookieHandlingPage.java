package com.qait.automation.PageObjectAutomation;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestCookieHandlingPage {
	WebDriver driver;
	String winHandleBefore;
	@FindBy(linkText = "Proceed")
	private WebElement Proceed;
	@FindBy(linkText = "Generate Token")
	private WebElement GenerateToken;
	@FindBy(id = "token")
	private WebElement TakeTokenValueGenerated;
	@FindBy(className="finish")
	WebElement Finish;
	public TestCookieHandlingPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public String returnEndText() {
		return Finish.getText();
	}
	void ProceedWithoutGeneratingToken() {
		Proceed.click();
	}
	void ProceedWithGeneratingTokenButNotAddingToken() {
		driver.get("http://10.0.1.86/tatoc/basic/cookie");
		GenerateToken.click();
		Proceed.click();
	}
	void ProceedWithGeneratingTokenAndAddingToken() {
		driver.get("http://10.0.1.86/tatoc/basic/cookie");
		GenerateToken.click();
		String[] tokenValue=TakeTokenValueGenerated.getText().split(" ");
		Cookie ck=new Cookie("Token", tokenValue[tokenValue.length-1]);
	    driver.manage().addCookie(ck);
		Proceed.click();
	}
}
