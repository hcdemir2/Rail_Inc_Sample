package com.railinc.pages;

import com.railinc.utils.Driver;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Getter
public abstract class BasePage {

    // Initialize elements in BasePage constructor
    public BasePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    // A small sample of elements common to all pages on public.railinc.com
    @FindBy(className = "page-title")
    private WebElement pageTitleElement;

    @FindBy(xpath = "//div[@id='header-container']//a[.='Customer Login']")
    private WebElement headerContainerCustomerLogin;

    @FindBy(xpath = "//div[@id='header-container']//a[.='Products & Services']")
    private WebElement headerContainerProductsAndServices;

    @FindBy(xpath = "//div[@id='header-container']//a[.='Resources']")
    private WebElement headerContainerResources;

    @FindBy(xpath = "//div[@id='header-container']//a[.='Support']")
    private WebElement headerContainerSupport;

    @FindBy(xpath = "//div[@id='header-container']//a[.='About Railinc']")
    private WebElement headerContainerAbout;

    @FindBy(xpath = "//div[@id='header-container']//a[.='Careers']")
    private WebElement headerContainerCareers;

    @FindBy(xpath = "//div[@id='header-container']//a[.='Contact Us']")
    private WebElement headerContainerContact;

    // Common methods to be used across all pages
    // Navigates to header tab that contains the parameter string
    public void navigateToHeaderTab(String tabText){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 20);
        String tabXpath = "//div[@id='header-container']//a[contains(text(), '"+ tabText +"')]";

        WebElement tabElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(tabXpath)));
        wait.until(ExpectedConditions.elementToBeClickable(tabElement)).click();
    }

    // Navigates to WebElement, does not wait Explicitly after click
    public void navigateTo(WebElement elementToClick){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 20);
        wait.until(ExpectedConditions.elementToBeClickable(elementToClick)).click();
    }

    // Navigates to WebElement, waits Explicitly for second WebElement
    public void navigateTo(WebElement elementToClick, WebElement elementToBeLoaded){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 20);
        wait.until(ExpectedConditions.elementToBeClickable(elementToClick)).click();
        wait.until(ExpectedConditions.visibilityOf(elementToBeLoaded));
    }
}
