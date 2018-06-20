package com.qait.automation.PageObjectAutomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestWindowsPage {
	WebDriver driver;
	String winHandleBefore;
	@FindBy(linkText = "Proceed")
	private WebElement Proceed;
	@FindBy(linkText = "Launch Popup Window")
	private WebElement LaunchPopupWindow;
	@FindBy(id = "name")
	private WebElement TextFieldInPopupWindow;
	@FindBy(id = "submit")
	private WebElement SubmitButtonInPopupWindow;
	public TestWindowsPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	void ClickProceedWithoutLaunchingPopupWindow() {
		Proceed.click();
	}
	void switchingToPopupWindow() {
		winHandleBefore = driver.getWindowHandle();
        for(String winHandle : driver.getWindowHandles()){
      	  driver.switchTo().window(winHandle);
        }
	}
	void ProceedWithLaunchingPopupWindowWithoutWritingAnythingInTextField() {
		driver.get("http://10.0.1.86/tatoc/basic/windows");
		LaunchPopupWindow.click();
		switchingToPopupWindow();
		SubmitButtonInPopupWindow.click();
		driver.switchTo().window(winHandleBefore);
		Proceed.click();
	}
	void ProceedWithLaunchingPopupWindowWithWritingAnythingInTextField() {
		driver.get("http://10.0.1.86/tatoc/basic/windows");
		LaunchPopupWindow.click();
		switchingToPopupWindow();
		TextFieldInPopupWindow.sendKeys("Anoop Kumar");
		SubmitButtonInPopupWindow.click();
		driver.switchTo().window(winHandleBefore);
		Proceed.click();
	}
}
