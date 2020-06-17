package functionLib;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Pagetitle {
	
	/*This Test case is validating if the page title is properly displaying or not after launching Amazon site.
	 * Implemented implicitlyWait
	 * */
	
	public static void main(String[] arg) {
		
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.com/");
		driver.manage().window().maximize();
		// WebDriverWait wait = new WebDriverWait(driver,9000);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		if (driver.getTitle().contains("Amazon.com")) {
			
			System.out.println("Test Case Pass");
		}
		else {
			System.out.println("Test Case Fail");
		}
		
		driver.close();
	}
	
	

}
