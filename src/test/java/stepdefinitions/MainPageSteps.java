package stepdefinitions;

import io.cucumber.java.en.Given;
import pages.MainPage;

public class MainPageSteps {
    MainPage mainPage = new MainPage();

    @Given("The User goes to the {string} page from the Main Page")
    public void gotoPage(String page){
        mainPage.clickNavigationLink(page);
    }










}
