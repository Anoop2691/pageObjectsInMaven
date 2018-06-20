package com.qait.automation.PageObjectAutomation;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;

public class NewTest {
	WebDriver driver;
	private TestBasicCourseLinkPage TestBasicCourseLinkPage;
	private TestGridGatePage TestGridGatePage;
	private TestFrameDungeonPage TestFrameDungeonPage;
	private TestDragAroundPage TestDragAroundPage;
	private TestWindowsPage TestWindowsPage;
	private TestCookieHandlingPage TestCookieHandlingPage;
	@BeforeClass
	void connectionWithChromeAndOpenLink() {
		driver = new ChromeDriver();
		driver.get("http://10.0.1.86/tatoc/");
		TestBasicCourseLinkPage= new TestBasicCourseLinkPage(driver);
		TestGridGatePage=new TestGridGatePage(driver);
		TestFrameDungeonPage=new TestFrameDungeonPage(driver);
		TestDragAroundPage=new TestDragAroundPage(driver);
		TestWindowsPage=new TestWindowsPage(driver);
		TestCookieHandlingPage=new TestCookieHandlingPage(driver);
		
	}
	
  @Test
  public void testBasicCourseLinkPage() {
	  TestBasicCourseLinkPage.clickOnWebElement();
	  Assert.assertTrue(TestBasicCourseLinkPage.returnPageHeadingText().contains("Grid Gate"));
  }
  @Test
  public void testGridGatePageForRedBox() {
	  TestGridGatePage.checkRedBox();
	  TestGridGatePage.returnErrorText();
	  Assert.assertTrue(TestGridGatePage.returnErrorText().contains("Error"));  
  }
@Test(dependsOnMethods="testGridGatePageForRedBox")
  public void testGridGatePageForGreenBox() {
	  testBasicCourseLinkPage();
	  TestGridGatePage.checkGreenBox();
	  Assert.assertTrue(TestBasicCourseLinkPage.returnPageHeadingText().contains("Frame Dungeon"));
  }
@Test(dependsOnMethods="testGridGatePageForGreenBox")
public void testFrameDungeonPage() {
	TestFrameDungeonPage.testFrameDungeonWhenColorIsDifferent();
	Assert.assertTrue(TestGridGatePage.returnErrorText().contains("Error"));
	Assert.assertTrue(TestFrameDungeonPage.testFrameDungeonWhenRepaintBoxIsClickedCheckChangeInBoxColor());
	TestFrameDungeonPage.testFrameDungeonWhenColorIsSame();
	Assert.assertTrue(TestBasicCourseLinkPage.returnPageHeadingText().contains("Drag Around"));
}
@Test(dependsOnMethods="testFrameDungeonPage")
public void testDragAroundPage() {
	TestDragAroundPage.PositionOfDragBoxNotChanged();
	Assert.assertTrue(TestGridGatePage.returnErrorText().contains("Error"));
	TestDragAroundPage.PositionOfDragBoxToPositionOfDropBox();
	Assert.assertTrue(TestBasicCourseLinkPage.returnPageHeadingText().contains("Popup Windows"));
	
}
@Test(dependsOnMethods="testDragAroundPage")
public void testWindowPage() {
	TestWindowsPage.ClickProceedWithoutLaunchingPopupWindow();
	Assert.assertTrue(TestGridGatePage.returnErrorText().contains("Error"));
	TestWindowsPage.ProceedWithLaunchingPopupWindowWithoutWritingAnythingInTextField();
	Assert.assertTrue(TestGridGatePage.returnErrorText().contains("Error"));
	TestWindowsPage.ProceedWithLaunchingPopupWindowWithWritingAnythingInTextField();
	Assert.assertTrue(TestBasicCourseLinkPage.returnPageHeadingText().contains("Cookie Handling"));
}
@Test(dependsOnMethods="testWindowPage")
public void testCookieHandlingPage() {
	TestCookieHandlingPage.ProceedWithoutGeneratingToken();
	Assert.assertTrue(TestGridGatePage.returnErrorText().contains("Error"));
	TestCookieHandlingPage.ProceedWithGeneratingTokenButNotAddingToken();
	Assert.assertTrue(TestGridGatePage.returnErrorText().contains("Error"));
	TestCookieHandlingPage.ProceedWithGeneratingTokenAndAddingToken();
	Assert.assertTrue(TestCookieHandlingPage.returnEndText().contains("End"));
}
}
