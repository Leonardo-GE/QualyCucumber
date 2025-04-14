package stepdefinitions;

import aquality.selenium.browser.AlertActions;
import aquality.selenium.browser.AqualityServices;
import io.cucumber.java.en.When;

public class BrowserSteps {



    @When("The User Accepts the Alert")
    public void AcceptAlert () {
        AqualityServices.getBrowser().handleAlert(AlertActions.ACCEPT);
    }
}
