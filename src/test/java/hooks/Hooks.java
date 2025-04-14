package hooks;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    private Browser browser = AqualityServices.getBrowser();


    @Before
    public void setup()
    {
        browser =  AqualityServices.getBrowser();
        browser.maximize();
        browser.goTo("https://the-internet.herokuapp.com");
    }

    @After
    public void teardown()
    {
        browser.quit();
    }







}
