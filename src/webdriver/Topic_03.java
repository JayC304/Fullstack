package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	} 

	@Test
	public void TC01_EmptyData() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		driver.findElement(By.xpath("//form//button[text()='ĐĂNG KÝ']")).click();
		
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
		
	}
	
	@Test
	public void TC02_InvalidEmail() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
				
		driver.findElement(By.id("txtFirstname")).sendKeys("Tran Anh");
		driver.findElement(By.id("txtEmail")).sendKeys("212@sdfsd@sdfs");
		driver.findElement(By.id("txtCEmail")).sendKeys("212@sdfsd@sdfs");
		driver.findElement(By.id("txtPassword")).sendKeys("Imip@123");
		driver.findElement(By.id("txtCPassword")).sendKeys("Imip@123");
		driver.findElement(By.id("txtPhone")).sendKeys("0333900300");		
		
		driver.findElement(By.xpath("//form//button[text()='ĐĂNG KÝ']")).click();
		
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
	}
	
	@Test
	public void TC03_IncorrectConfirmEmail() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
				
		driver.findElement(By.id("txtFirstname")).sendKeys("Tran Anh");
		driver.findElement(By.id("txtEmail")).sendKeys("trananh@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("trananh2022@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("Imip@123");
		driver.findElement(By.id("txtCPassword")).sendKeys("Imip@123");
		driver.findElement(By.id("txtPhone")).sendKeys("0333900300");		
		
		driver.findElement(By.xpath("//form//button[text()='ĐĂNG KÝ']")).click();
		
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
	}
	
	@Test
	public void TC04_IncorrectPassword() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
				
		driver.findElement(By.id("txtFirstname")).sendKeys("Tran Anh");
		driver.findElement(By.id("txtEmail")).sendKeys("trananh@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("trananh@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("Imip");
		driver.findElement(By.id("txtCPassword")).sendKeys("Imip");
		driver.findElement(By.id("txtPhone")).sendKeys("0333900300");		
		
		driver.findElement(By.xpath("//form//button[text()='ĐĂNG KÝ']")).click();
		
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
	}
	
	@Test
	public void TC05_IncorrectComfirmPassword() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
				
		driver.findElement(By.id("txtFirstname")).sendKeys("Tran Anh");
		driver.findElement(By.id("txtEmail")).sendKeys("trananh@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("trananh@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("Imip@2022");
		driver.findElement(By.id("txtCPassword")).sendKeys("Imip@2021");
		driver.findElement(By.id("txtPhone")).sendKeys("0333900300");		
		
		driver.findElement(By.xpath("//form//button[text()='ĐĂNG KÝ']")).click();
		
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");
	}
	
	@Test
	public void TC06_InvalidPhoneNumber() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
				
		driver.findElement(By.id("txtFirstname")).sendKeys("Tran Anh");
		driver.findElement(By.id("txtEmail")).sendKeys("trananh@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("trananh@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("Imip@2022");
		driver.findElement(By.id("txtCPassword")).sendKeys("Imip@2022");
		driver.findElement(By.id("txtPhone")).sendKeys("033390030000");		
		
		driver.findElement(By.xpath("//form//button[text()='ĐĂNG KÝ']")).click();
		
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
