package com.railinc.step_definitions;

import com.railinc.pages.ProductsAndServicesPage;
import com.railinc.utils.ConfigReader;
import com.railinc.utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class SearchProductsAndServicesStepDefinitions {

    WebDriver driver = Driver.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, 20);
    ProductsAndServicesPage productsAndServicesPage = new ProductsAndServicesPage();

    // Scenario 1
    @Given("public user is on the home page")
    public void public_user_is_on_the_home_page() {
        driver.get(ConfigReader.getProperty("public.railinc.url"));
    }

    @Given("navigates to the Products & Services page")
    public void navigates_to_the_products_services_page() {
        productsAndServicesPage.navigateToHeaderTab("Products & Services");
    }

    @Then("user should see at least {int} results")
    public void user_should_see_at_least_results(Integer expectedNumOfResults) {
        Assertions.assertThat(productsAndServicesPage.getAllSearchResults().size()>=expectedNumOfResults);
    }


    // Scenario 2
    @When("user enters valid {string} in search box and clicks apply button")
    public void user_enters_valid_in_search_box(String search_term) {
        productsAndServicesPage.writeInProductsAndServicesSearchBox(search_term);
        wait.until(ExpectedConditions.elementToBeClickable(productsAndServicesPage.getApplyBtn())).click();
    }

    @Then("title of each result contains {string}")
    public void title_of_each_result_contains(String search_term) {
        for (WebElement eachResult : productsAndServicesPage.getAllSearchResultsLinks()){
            Assertions.assertThat(eachResult.getText().contains(search_term));
        }
    }


    // Scenario 3
    @When("user searches by category {string}")
    public void user_searches_by_category(String category) {
        productsAndServicesPage.selectCategoryDropdownElement(category);
        wait.until(ExpectedConditions.elementToBeClickable(productsAndServicesPage.getApplyBtn())).click();
    }

    @Then("user should see less than {int} results")
    public void user_should_see_less_than_results(Integer totalProducts) {
        Assertions.assertThat(productsAndServicesPage.getAllSearchResults().size()<totalProducts);
    }


    // Scenario 4
    private List<String> categoryProductsTitles;

    @When("user sees less than {int} results")
    public void user_sees_less_than_results(Integer expectedNumOfResults) {
        Assertions.assertThat(productsAndServicesPage.getAllSearchResults().size()>=expectedNumOfResults);

        // Store titles of all Products for validation later
        categoryProductsTitles = new ArrayList<>();
        productsAndServicesPage.getAllSearchResultsLinks().forEach( eachElement -> categoryProductsTitles.add(eachElement.getText()) );
    }

    @Then("each result belongs to the category chosen")
    public void each_result_belongs_to_the_category_chosen() {
        driver.navigate().refresh();  // To overcome stale element exception and relocate allSearchResults
        for (WebElement eachResult : productsAndServicesPage.getAllSearchResultsLinks()){
            Assertions.assertThat(categoryProductsTitles.contains(eachResult.getText()));
        }
    }

}
