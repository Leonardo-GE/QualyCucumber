package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.forms.Form;
import constants.LocatorConstants;
import org.openqa.selenium.By;
import org.testng.Assert;

public class CheckboxPage extends Form {

    private static final String PAGE_NAME = "Checkboxes";
    private final IElementFactory elementFactory = AqualityServices.getElementFactory();
    private final By checkBox1 = By.xpath("//*[@id=\"checkboxes\"]/input[1]");
    private final By checkBox2 = By.xpath("//*[@id=\"checkboxes\"]/input[2]");
    private final ICheckBox checkBox1IBX = elementFactory.getCheckBox(checkBox1,"Checkbox1");
    private final ICheckBox checkBox2IBX = elementFactory.getCheckBox(checkBox2,"Checkbox2");



    public CheckboxPage() {
        super(By.xpath(String.format(LocatorConstants.PRECISE_TEXT_XPATH, PAGE_NAME)), PAGE_NAME);
    }


    public void checkFirstCheckbox() {

        if (checkBox1IBX.isChecked()){

        } else {
            checkBox1IBX.check();
        }
    }

    public void checkSecondCheckbox() {
        if (checkBox2IBX.isChecked()){
            checkBox2IBX.uncheck();
        }
    }


    public boolean isCheckbox1Visible(){
        return checkBox1IBX.state().isDisplayed();
    }

    public boolean isCheckbox2Visible(){
        return checkBox2IBX.state().isDisplayed();
    }


    public boolean checkBox1Status() {
        return checkBox1IBX.isChecked();
    }


    public boolean checkBox2Status() {
        return checkBox2IBX.isChecked();
    }





}
