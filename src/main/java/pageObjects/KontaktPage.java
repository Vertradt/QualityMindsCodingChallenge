package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class KontaktPage extends PageObject {
    @FindBy(xpath = "//a[@href=\"mailto:hello@qualityminds.de\"]")
    private WebElement email;


    public KontaktPage(WebDriver driver) {
        super(driver);
    }


    public WebElement getEmail() {
        return email;
    }
}
