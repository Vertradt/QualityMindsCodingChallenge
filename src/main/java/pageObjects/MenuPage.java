package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MenuPage extends PageObject {
    @FindBy(xpath = "//a[@href=\"https://qualityminds.de/remote-team-master/\"][1]")
    private WebElement testMasterButton;


    @FindBy(xpath = "//a[@href=\"https://qualityminds.de/team_page/wam-testing/\"][1]")
    private WebElement WAMTesting;


    public MenuPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getTestMasterButton() {
        return testMasterButton;
    }


    public WebElement getWAMTesting() {
        return WAMTesting;
    }
}
