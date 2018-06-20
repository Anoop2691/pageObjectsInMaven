package com.qait.automation.PageObjectAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;

public class TestGridGatePage {
	WebDriver driver;
	
	public TestGridGatePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(className="redbox")
	WebElement RedBoxElementRetrieved;
	@FindBy(className="greenbox")
	WebElement GreenBoxElementRetrieved;
	@FindBy(className="error")
	WebElement GetErrorElement;
	
	void checkRedBox() {
		RedBoxElementRetrieved.click();
	}
	public String returnErrorText() {
		return GetErrorElement.getText();
	}
	void checkGreenBox() {
		GreenBoxElementRetrieved.click();
	}
	public WebElement getPageHeadingText() {
		return driver.findElement(By.className("page"));
	}
	public String returnPageHeadingText() {
		return getPageHeadingText().getText();
	}
	
}
