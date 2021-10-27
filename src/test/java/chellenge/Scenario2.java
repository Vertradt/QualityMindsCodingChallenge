package chellenge;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Reporter;
import org.testng.annotations.*;
import pageObjects.HomePage;
import pageObjects.KontaktPage;
import pageObjects.MenuPage;
import pageObjects.PortfolioPage;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class Scenario2 {


    WebDriver driver = null;
    private String qMPageUrl = "https://qualityminds.de/";
    private HomePage homePage;
    private KontaktPage kontaktPage;
    private MenuPage menuPage;
    private PortfolioPage portfolioPage;
    private String contactPageURL = "https://qualityminds.de/kontakt/";
    private String step3Url;
    private String step5Url;

    @Parameters("browserName")
    @BeforeClass
    public void setUp(@Optional String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            //initializing and starting the Chromebrowser
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            //initializing and starting the Chromebrowser
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
        kontaktPage = new KontaktPage(driver);
        menuPage = new MenuPage(driver);
        portfolioPage = new PortfolioPage(driver);
        driver.get(qMPageUrl);
    }

    @Test
    public void assertThatQualityMindsPageIsOpened() {
        //maximize the window
        assertEquals(driver.getCurrentUrl(), qMPageUrl);
        Reporter.log("QualityMinds page is opened");

    }

    @Test(dependsOnMethods =
            {"assertThatQualityMindsPageIsOpened"})
    public void assertThatSubmenuIsDisplayed() {
        homePage.hoveringOverOverviewButton();
        assertTrue(menuPage.getTestMasterButton().isDisplayed());
        assertTrue(menuPage.getWAMTesting().isDisplayed());
        Reporter.log("Submenu is displayed");
    }

    //
    @Test(dependsOnMethods =
            {"assertThatQualityMindsPageIsOpened",
                    "assertThatSubmenuIsDisplayed"})
    public void assertThatWebAutomationAndMobileTestingPageIsDisplayed() {
        menuPage.getWAMTesting().click();
        assertEquals(driver.getCurrentUrl(), "https://qualityminds.de/team_page/wam-testing/");
        Reporter.log("Web, Automation & Mobile Testing page is displayed");

    }

    @Test(dependsOnMethods =
            {"assertThatQualityMindsPageIsOpened",
                    "assertThatSubmenuIsDisplayed",
                    "assertThatWebAutomationAndMobileTestingPageIsDisplayed"})
    public void assertThatPortfolioItemOfTheTopBarMenuIsHighlighted() {
        String actualColor = homePage.getColorOfSubMenuButton();
        String hexOfBlack = "#000000";
        assertNotEquals(actualColor, hexOfBlack);
        Reporter.log("Portfolio item of the top bar menu is highlighted");

    }

    @Test(dependsOnMethods =
            {"assertThatQualityMindsPageIsOpened",
                    "assertThatSubmenuIsDisplayed",
                    "assertThatWebAutomationAndMobileTestingPageIsDisplayed",
                    "assertThatPortfolioItemOfTheTopBarMenuIsHighlighted"})
    public void assertThatMobileTabIsSelected() throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", portfolioPage.getMobileButton());
        Thread.sleep(500);

        portfolioPage.getMobileButton().click();
//        assertTrue(portfolioPage.getFindTheMobileBugButton().isDisplayed());
        System.out.println(Color.fromString(portfolioPage.getMobileButton().getCssValue("color")).asHex());

    }


    @AfterTest
    public void tearDown() {

        driver.quit();
    }
}
