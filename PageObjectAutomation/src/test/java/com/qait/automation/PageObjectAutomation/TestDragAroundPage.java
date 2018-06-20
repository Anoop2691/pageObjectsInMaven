package com.qait.automation.PageObjectAutomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestDragAroundPage {
	WebDriver driver;
	@FindBy(linkText = "Proceed")
	private WebElement Proceed;
	@FindBy(id="dragbox")
	private WebElement DragBox;
	@FindBy(id="dropbox")
	private WebElement DropBox;
	public TestDragAroundPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	void PositionOfDragBoxToPositionOfDropBox() {
		driver.get("http://10.0.1.86/tatoc/basic/drag");
		new Actions(driver).dragAndDrop(DragBox,DropBox).perform();
		Proceed.click();
	}
	void PositionOfDragBoxNotChanged() {
		Proceed.click();
	}
}
