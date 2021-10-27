package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;


public class HomePage extends PageObject {
    @FindBy(xpath = "//a[@href=\"https://qualityminds.de/kontakt/\"][1]")
    private WebElement kontaktButton;

    @FindBy(className = "bottom-nav")
    private WebElement bottomNav;

    @FindBy(xpath = "//a[@href=\"https://qualityminds.de/team-overview/\"][1]")
    private WebElement subMenuButton;


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public WebElement getKontaktButton() {
        return kontaktButton;
    }

    public WebElement getBottomNav() {
        return bottomNav;
    }

    public void hoveringOverOverviewButton() {
        Actions actions = new Actions(driver);
        actions.moveToElement(subMenuButton).build().perform();
    }

    public String getColorOfSubMenuButton() {
        return Color.fromString(subMenuButton.getCssValue("color")).asHex();
    }


    public WebElement getSubMenuButton() {
        return subMenuButton;
    }
}
