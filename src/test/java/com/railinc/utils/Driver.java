package com.railinc.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Driver {

    private Driver(){ }

    // For parallel execution, driverPool would be made using ThreadLocal
    private static WebDriver driver;

    // For parallel execution, Driver.class would be synchronized
    // For remote execution, additional case statements would be used (i.e. remote-chrome, remote-firefox, etc.)
    //     to set DesiredCapabilities and URL, and execute using RemoteWebDriver
    public static WebDriver getDriver(){

        if(driver==null){
            String browser = ConfigReader.getProperty("browser");
            switch (browser){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
            }
        }
        return driver;
    }

    public static void closeDriver(){
        if(driver!=null){
            driver.quit();
            driver = null;
        }
    }
}
