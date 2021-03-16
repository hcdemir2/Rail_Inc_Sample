package com.railinc.pages;

import com.railinc.utils.Driver;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

@Getter
public class ProductsAndServicesPage extends BasePage {

    @FindBy(id = "edit-product--2")
    private WebElement productServiceNameInput;

    @FindBy(id = "edit-category--2")
    private WebElement categoryDropdownElement;

    @FindBy(id = "edit-submit-products-index--2")
    private WebElement applyBtn;

    @FindBy(className = "views-row")
    private List<WebElement> allSearchResults;

    @FindBy(xpath = "//span[@class='field-content']/a")
    private List<WebElement> allSearchResultsLinks;

    public void writeInProductsAndServicesSearchBox(String input){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(),20);
        wait.until(ExpectedConditions.elementToBeClickable(productServiceNameInput)).sendKeys(input);
    }

    public void selectCategoryDropdownElement(String category){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(),20);
        wait.until(ExpectedConditions.visibilityOf(categoryDropdownElement));
        Select categorySelect = new Select(categoryDropdownElement);
        categorySelect.selectByVisibleText(category);
    }

}
