package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.CheckboxPage;

public class CheckboxSteps {

    CheckboxPage checkboxPage = new CheckboxPage();


    @When("User checks first checkbox")
    public void checkFirstCheckbox () {
        checkboxPage.checkFirstCheckbox();
    }


    @When("User unchecks second checkbox")
    public void uncheckSecondCheckbox () {
        checkboxPage.checkSecondCheckbox();
    }


    @When("User verifies checkboxes exist")
    public void verifyCheckboxExist(){
        Assert.assertTrue(checkboxPage.isCheckbox1Visible(),"The Checkbox one is not visible");
        Assert.assertTrue(checkboxPage.isCheckbox2Visible(),"The Checkbox two is not Visible");
    }


    @Then("The 1st checkbox should be checked and 2nd should be unchecked")
    public void verifyCheckboxStatus(){
        Assert.assertTrue(checkboxPage.checkBox1Status(),"The First Checkbox is not selected");
        Assert.assertFalse(checkboxPage.checkBox2Status(),"The Second Checkbox is Selected");
    }




}
