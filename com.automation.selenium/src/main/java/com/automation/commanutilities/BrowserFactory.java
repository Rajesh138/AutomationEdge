package com.automation.commanutilities;

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
	String folderpath="C:\\git\\AE-Automation\\AutomationEdge\\com.automation.selenium\\src\\downloads";

	public WebDriver startBrowser(String browserName, String URL) {
		Reporter.log("=====Browser Session Started=====", true);
		if (browserName.equalsIgnoreCase("firefox")) {
			webDriver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "..\\com.automation.selenium\\src\\drivers\\ChromeDriver74.exe");

			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("download.default_directory", folderpath );
					
					/*System.getProperty("user.dir") + File.separator + "externalFiles"
					+ File.separator + "downloadFiles");*/
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			
			webDriver = new ChromeDriver(options);
		} else if (browserName.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", "..\\com.automation.selenium\\src\\drivers\\IEDriverServer.exe");
			webDriver = new InternetExplorerDriver();
		}
		Reporter.log("=====Application Started=====", true);

		webDriver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS); 
		webDriver.manage().window().maximize();
		webDriver.get(URL);

		return webDriver;
	}

}
