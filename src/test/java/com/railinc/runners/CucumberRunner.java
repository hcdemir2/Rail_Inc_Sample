package com.railinc.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "rerun:target/rerun.txt",
                "json:target/cucumber.json",
                "html:target/cucumber-reports.html"
        },
        features = {
                "src/test/resources"
        },
        glue = "com/railinc/step_definitions",
        dryRun = false,
        tags = "@products"
)

public class CucumberRunner {
}
