package com.automation.commanutilities;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Reporter;

public class BrowserFactory {
	public WebDriver webDriver;
	String path = System.getProperty("user.dir") + "/src/drivers";
	String fileDownloadPath = "C:\\Users\\Admin\\Downloads\\aeUto";
	public WebDriver startBrowser(String browserName, String URL) {
		Reporter.log("=====Browser Session Started=====", true);
		if (browserName.equalsIgnoreCase("firefox")) {
			webDriver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", path + "/ChromeDriver75.exe");

			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("download.default_directory", fileDownloadPath );
					
					/*System.getProperty("user.dir") + File.separator + "externalFiles"
					+ File.separator + "downloadFiles");*/
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			
			webDriver = new ChromeDriver(options);
			
			// ChromeDriver driver = new ChromeDriver(options);
		} else if (browserName.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", "C:\\Eclise_work\\Drivers\\IEDriverServer.exe");
			webDriver = new InternetExplorerDriver();
		}
		Reporter.log("=====Application Started=====", true);

		webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		webDriver.manage().window().maximize();
		webDriver.get(URL);

		return webDriver;
	}

}
