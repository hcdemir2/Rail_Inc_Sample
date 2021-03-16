package com.railinc.step_definitions;

import com.railinc.utils.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;

public class Hooks {
    @Before
    public void setup(Scenario scenario) {
        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] data = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(data, "image/png", scenario.getName());
        }

        Driver.closeDriver();
    }

    // Used to establish database connection for Scenarios with DB validations
    @Before(value = "@db")
    public void dbSetup() {
        // Create DB connection here
    }

    @After(value = "@db")
    public void dbTearDown() {
        // Close ResultSet, Statement, Connection
    }
}
