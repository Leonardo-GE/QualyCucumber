package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.JavaScriptAlertsPage;

public class JavaScriptAlertPageSteps {
    JavaScriptAlertsPage javaScriptAlertsPage = new JavaScriptAlertsPage();



    @When("The User generated the JS Alert")
    public void generateJSAlert () {
        javaScriptAlertsPage.clickForJsAlertBtn();
    }

    @Then("The Success message is correctly displayed")
    public void acceptAlert (){
        Assert.assertTrue(javaScriptAlertsPage.isSuccessLabelDisplayed(),"SuccessMessage is not Displayed");
    }











}
