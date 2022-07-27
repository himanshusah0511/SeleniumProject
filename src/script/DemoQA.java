package script;

import static org.testng.Assert.*;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.netty.util.Timeout;

public class DemoQA {

	WebDriver cd=null;
	
	@Test()
	public void Script() throws InterruptedException
	{
		String acutalURL= cd.getCurrentUrl();
		String expectedURL= "https://demoqa.com/alertsWindows";
		Assert.assertEquals(acutalURL, expectedURL);
		System.out.println("Current URL:"+acutalURL);
		String acutalTitle= cd.getTitle();
		String expectedTitle= "ToolsQA";
		Assert.assertEquals(acutalTitle, expectedTitle);
		System.out.println("Current URL:"+acutalTitle);
		WebElement Alert = cd.findElement(By.xpath("//span[.='Alerts']"));
		WebElement ModalDialogs = cd.findElement(By.xpath("//span[.='Modal Dialogs']"));
		
		Actions actions = new Actions(cd);
		actions.moveToElement(ModalDialogs).perform();	
		Alert.click();
		
		cd.findElement(By.id("timerAlertButton")).click();
		WebDriverWait wait = new WebDriverWait(cd, Duration.ofSeconds(30));
		wait.until((ExpectedConditions.alertIsPresent()));
		cd.switchTo().alert().accept();
		String CurrentWindow=cd.getWindowHandle();
		WebElement NestedFrames = cd.findElement(By.xpath("//span[.='Nested Frames']"));
		JavascriptExecutor js = (JavascriptExecutor) cd;
		//Scroll down till the bottom of the page
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

		
	//	actions.moveToElement(ModalDialogs).perform();
	//	actions.moveToElement(NestedFrames).perform();
		NestedFrames.click();
		WebElement ParentFrame = cd.findElement(By.xpath("//iframe[@id='frame1']"));
		System.out.println("Switch to Parent Frame");
		cd.switchTo().frame(ParentFrame);
		WebElement ChildFrame = cd.findElement(By.xpath("//iframe[@srcdoc='<p>Child Iframe</p>']"));
		System.out.println("Switch to Child Frame");
		cd.switchTo().frame(ChildFrame);
		System.out.println("Switching back to Parent Frame");
		cd.switchTo().parentFrame();
	
		String NestedFrameURL="https://demoqa.com/nestedframes";
		Assert.assertEquals(NestedFrameURL, cd.getCurrentUrl());
		String acutalTitle2= cd.getTitle();
		String expectedTitle2= "ToolsQA";
		Assert.assertEquals(acutalTitle2, expectedTitle2);
		System.out.println("Current URL:"+acutalTitle2);
	}
	
	@SuppressWarnings("deprecation")
	@BeforeMethod
	public void Close()
	{
		String url="https://demoqa.com/alertsWindows";
		System.setProperty("webdriver.chrome.driver", "Drivers\\chromedriver.exe");			
		cd= new ChromeDriver();
		cd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
		cd.manage().window().maximize();
		cd.get(url);
	}
	
	@AfterMethod()
	public void Setup()
	{
		cd.close();
	}
	
}
