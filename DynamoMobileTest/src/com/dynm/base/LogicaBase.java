package com.dynm.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogicaBase {

	// Variable estática del driver ya que lo usaremos como un valor fijo dentro de
		// cada método
		// Seteo de Paths de los distintos browsers
		public String ChromePath = "C:\\Eclipse\\workspace\\DynamoMobileTest\\Drivers\\chromedriver.exe";
		public static WebDriver driver;
		public static WebDriverWait wait;
		public static WebElement busquedaDisplay;
		public static String pageLoadStatus;

		public void setUpBrowser() {
			System.setProperty("webdriver.chrome.driver", ChromePath);

			// Aca podriamos agregar parameters para especificar algunas cuestiones
			// Como por ejemplo donde esta el binary de nuestra V del browser,
			// si deseamos usar alguna alternativa al default.

			/*
			 * ChromeOptions cop = new ChromeOptions(); cop.setBinary(path)
			 */

			driver = new ChromeDriver();

			// Maximizar la ventana
			driver.manage().window().maximize();

			// Aca me gusta hacer esto que no lo hace nadie, suele depender un poco del TC
			// pero por lo general...
			Set<Cookie> allCookies = driver.manage().getCookies();
			for (Cookie cookie : allCookies) {
				driver.manage().deleteCookieNamed(cookie.getName());
			}
		}
		
		public static String timeStamp() {
			return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
		}

		public static void takeScreenshot(String script, String metodo) {

			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				FileHandler.copy(src, new File("C:\\Eclipse\\workspace\\DynamoMobileTest\\Screenshots\\" 
			+ script + "_" + metodo + "_" + timeStamp() + ".png"));
				
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		
		public static void waitForPageToLoad() {
			do {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				pageLoadStatus = (String) js.executeScript("return document.readyState");
				System.out.print(".");
			} while (!pageLoadStatus.equals("complete"));
			System.out.println();
			System.out.println("Page Loaded.");
		}

	
}
