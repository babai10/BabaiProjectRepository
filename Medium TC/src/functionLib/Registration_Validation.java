package functionLib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Registration_Validation {
	
	/* This Test case is validating if Error message is displaying for wrong Login credentials during signin to Amazon site.
	 * Also checking alerts if mandatory inputs are blank in account create page
	Implemented Apache - POI to read excel and get the email ID as input.
	*/
	
	public static void main(String[] arg) throws InterruptedException, IOException {
		
		FileInputStream file = new FileInputStream(new File("src/Input/DataSheet.xlsx"));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(0);
		String email = sheet.getRow(1).getCell(0).getStringCellValue();
		System.out.println("email = "+ email);
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		 WebDriverWait wait = new WebDriverWait(driver,90000);
	//	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Hello, Sign in')]")));
		driver.findElement(By.xpath("//span[contains(text(),'Hello, Sign in')]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='ap_email']")));
		driver.findElement(By.xpath("//input[@id='ap_email']")).sendKeys(email); 
		driver.findElement(By.xpath("//input[@id='continue']")).click();
		
		try{
			
			WebElement invEmailAlart = driver.findElement(By.xpath("//h4[contains(text(),'There was a problem')]"));
			
			if(invEmailAlart.isDisplayed()) {
				System.out.println("Pass: Alart message is displaying successfully");
			}
			else {
				System.out.println("Fail: Alart message is Not displaying");
				
			}
		}
		
		catch(Exception e) {
			
			System.out.println("Fail: Alart message is Not displaying");
		}
		//driver.wait();
		driver.findElement(By.xpath("//a[@id='createAccountSubmit']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Create account')]")));
		driver.findElement(By.xpath("//input[@id='continue']")).click();
		
		try{
			
			WebElement username = driver.findElement(By.xpath("//div[@id='auth-customerName-missing-alert']"));
			
			if(username.isDisplayed()) {
				System.out.println("Pass: UserName Blank - Alart message is displaying successfully");
			}
			else {
				System.out.println("Fail: UserName Blank - Alart message is Not displaying");
				
			}
		}
		
		catch(Exception e) {
			
			System.out.println("Fail: UserName Blank - Alart message is Not displaying");
		}
		
		try{
			
			WebElement emailID = driver.findElement(By.xpath("//div[@id='auth-email-missing-alert']"));
			
			if(emailID.isDisplayed()) {
				System.out.println("Pass: UserEmailID Blank - Alart message is displaying successfully");
			}
			else {
				System.out.println("Fail: UserEmailID Blank - Alart message is Not displaying");
				
			}
		}
		
		catch(Exception e) {
			
			System.out.println("Fail: UserEmailID Blank - Alart message is Not displaying");
		}
		try{
			
			WebElement passwordAlrt = driver.findElement(By.xpath("//div[@id='auth-password-missing-alert']"));
			
			if(passwordAlrt.isDisplayed()) {
				System.out.println("Pass: UserPassword Blank - Alart message is displaying successfully");
			}
			else {
				System.out.println("Fail: UserPassword Blank - Alart message is Not displaying");
				
			}
		}
		
		catch(Exception e) {
			
			System.out.println("Fail: UserPassword Blank - Alart message is Not displaying");
		}
		
		
		driver.close();
	}
	
	
	
	
	

}
