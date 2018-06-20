package com.qait.automation.PageObjectAutomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestFrameDungeonPage {
	WebDriver driver;
	
	public TestFrameDungeonPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(linkText = "Proceed")
	private WebElement Proceed;
	@FindBy(linkText = "Repaint Box 2")
	private WebElement RepaintBox;
	@FindBy(xpath = "//*[@id=\"answer\"]")
	private WebElement findIdOfBoxOne;
	@FindBy(xpath = "//div[@id=\"answer\"]")
	private WebElement findIdOfBoxTwo;
	
	public void testFrameDungeonWhenColorIsDifferent() {
		driver.switchTo().frame("main");
        String ColorOfBoxOne=findIdOfBoxOne.getAttribute("class");
        driver.switchTo().frame("child");
        String ColorOfBoxTwo=findIdOfBoxTwo.getAttribute("class");
        driver.switchTo().parentFrame();
        if(!ColorOfBoxOne.equals(ColorOfBoxTwo)) {
        	Proceed.click();
        }
	}
	public boolean testFrameDungeonWhenRepaintBoxIsClickedCheckChangeInBoxColor() {
        driver.get("http://10.0.1.86/tatoc/basic/frame/dungeon");
        driver.switchTo().frame("main");
		driver.switchTo().frame("child");
        String ColorOfBoxTwo=findIdOfBoxTwo.getAttribute("class");
        driver.switchTo().parentFrame();
        RepaintBox.click();
        driver.switchTo().frame("child");
        String ColorOfBoxTwoAfterRepaint=findIdOfBoxTwo.getAttribute("class");
        if(!ColorOfBoxTwo.equals(ColorOfBoxTwoAfterRepaint)) {
        	return true;
        }
        else return false;
	}
	public void testFrameDungeonWhenColorIsSame() {
		driver.get("http://10.0.1.86/tatoc/basic/frame/dungeon");
		driver.switchTo().frame("main");
        String ColorOfBoxOne=findIdOfBoxOne.getAttribute("class");
        String ColorOfBoxTwo="";
        do {
        	driver.switchTo().frame("child");
            ColorOfBoxTwo=findIdOfBoxTwo.getAttribute("class");
            driver.switchTo().parentFrame();
        	if(ColorOfBoxOne.equals(ColorOfBoxTwo)) {
        		Proceed.click();
        	}
        	else {
        		RepaintBox.click();
        	}
        }while(!ColorOfBoxOne.equals(ColorOfBoxTwo));
	}
}
