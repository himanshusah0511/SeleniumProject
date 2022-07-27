package script;

import static org.testng.Assert.*;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.netty.util.Timeout;

public class Facebook {

	WebDriver cd=null;
	
	@Test()
	public void Script()
	{
		String acutalTitle= cd.getTitle();
		String expectedTitle= acutalTitle;
	//	Assert.assertEquals(acutalTitle, expectedTitle);
		WebElement InfoIcon= cd.findElement(By.xpath("//*[.='Date of birth']//following-sibling :: a"));
		InfoIcon.click();
		String DOBText= cd.findElement(By.xpath("//div[@class='_9hzn']")).getText();
		System.out.println(DOBText);
		InfoIcon.click();
		System.out.println(cd.getCurrentUrl()+cd.getTitle());
		WebElement FirstName= cd.findElement(By.name("firstname"));
		FirstName.sendKeys("Himanshu");
		WebElement LastName= cd.findElement(By.name("lastname"));
		LastName.sendKeys("Sah");
		WebElement MobileOREmail= cd.findElement(By.name("reg_email__"));
		MobileOREmail.sendKeys("8979567675");
		WebElement Password= cd.findElement(By.id("password_step_input"));
		Password.sendKeys("NewPassword0511");
		WebElement Day= cd.findElement(By.name("birthday_day"));
		Select DayDD= new Select(Day);
		DayDD.selectByValue("5");
		WebElement Month= cd.findElement(By.name("birthday_month"));
		Select MonthDD= new Select(Month);
		MonthDD.selectByVisibleText("Nov");
		WebElement Year= cd.findElement(By.name("birthday_year"));
		Select YearDD= new Select(Year);
		YearDD.selectByVisibleText("1994");
		
		WebElement genderF= cd.findElement(By.xpath("//input[@type='radio' and @name='sex' and @value='1']"));
		WebElement genderM= cd.findElement(By.xpath("//input[@type='radio' and @name='sex' and @value='2']"));
		WebElement genderO= cd.findElement(By.xpath("//input[@type='radio' and @name='sex' and @value='-1']"));
		
		genderM.click();
		WebElement SignupButton= cd.findElement(By.name("websubmit"));
		SignupButton.click();
		System.out.println(cd.getWindowHandle());
		WebDriverWait wait = new WebDriverWait(cd, Duration.ofSeconds(30));
		wait.until((ExpectedConditions.urlContains("www.facebook.com/checkpoint")));
		System.out.println(cd.getWindowHandle());
		System.out.println(cd.getCurrentUrl());
		String ExpectedText1 = "We'll take you through a few steps to confirm your account on Facebook.";
		String ExpectedText2 = "Complete these steps in the next 28 days to make sure that you can use this account.";
						
		
		String Text1 = cd.findElement(By.xpath("//span//div[contains(@class,'hcukyx3x c1et5uql')]")).getText();
		String Text2 = cd.findElement(By.xpath("//span//div[contains(@class,'hcukyx3x c1et5uql')]//following-sibling :: div")).getText();
		System.out.println(Text1);
		System.out.println(Text2);

		Assert.assertEquals(Text1,ExpectedText1);
		Assert.assertEquals(Text2, ExpectedText2, "Pass");
		
		
	}
	
	@SuppressWarnings("deprecation")
	@BeforeMethod
	public void Close()
	{
		String url="https://www.facebook.com/reg";
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
