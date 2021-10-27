package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PortfolioPage extends PageObject {
    @FindBy(css = "div[id=\"team-tab-three-title-desktop\"]")
    private WebElement mobileButton;

    @FindBy(xpath = "a[href=\"https://qualityminds.de/sites/default/files/content/WYSIWYG/find-the-bug-session_0.pdf\"]")
    private WebElement findTheMobileBugButton;


    public PortfolioPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getMobileButton() {
        return mobileButton;
    }

    public WebElement getFindTheMobileBugButton() {
        return findTheMobileBugButton;
    }
}
