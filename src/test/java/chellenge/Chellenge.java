package chellenge;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.*;
import pageObjects.HomePage;
import pageObjects.KontaktPage;
import pageObjects.MenuPage;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

public class Chellenge {

    WebDriver driver = null;
    private String qMPageUrl = "https://qualityminds.de/";
    private HomePage homePage;
    private KontaktPage kontaktPage;
    private MenuPage menuPage;
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
        driver.get(qMPageUrl);
    }


    @Test
    public void assertThatQualityMindsPageIsOpened() {
        //maximize the window
        assertEquals(driver.getCurrentUrl(), qMPageUrl);
        Reporter.log("QualityMinds page is opened");

    }


    @Test(dependsOnMethods = "assertThatQualityMindsPageIsOpened")
    public void assertThatKontaktAndAnfahrtPageIsDisplayed() {
        homePage.getKontaktButton().click();
        step3Url = kontaktPageAssertions(driver);
    }

    private String kontaktPageAssertions(WebDriver driver) {
        String url = driver.getCurrentUrl();
        System.out.println(url);
        assertEquals(url, contactPageURL);
        Reporter.log("Kontakt & Anfahrt page is displayed");
        return url;
    }

    @Test(dependsOnMethods = {"assertThatQualityMindsPageIsOpened", "assertThatKontaktAndAnfahrtPageIsDisplayed"})
    public void assertThatContainsEmailAddress() {
        String emailFromPage = kontaktPage.getEmail().getText();
        String correctEmail = "hello@qualityminds.de";
        assertTrue(emailFromPage.contains(correctEmail));
        Reporter.log("Page contains hello@qualityminds.de email address");

    }

    @Test(dependsOnMethods =
            {"assertThatQualityMindsPageIsOpened",
                    "assertThatKontaktAndAnfahrtPageIsDisplayed",
                    "assertThatContainsEmailAddress"})
    public void navigateBackToMainPage() {
        driver.navigate().back();
//        assertThatQualityMindsPageIsOpened();
//        assertEquals(driver.getCurrentUrl(), qMPageUrl);
        assertEquals(driver.getCurrentUrl(), qMPageUrl);
        Reporter.log("QualityMinds page is opened");
    }

    @Test(dependsOnMethods =
            {"assertThatQualityMindsPageIsOpened",
                    "assertThatKontaktAndAnfahrtPageIsDisplayed",
                    "assertThatContainsEmailAddress"
                    , "navigateBackToMainPage"})
    public void KontaktAndAnfahrtPageIsDisplayed() {
        List<WebElement> elements = homePage.getBottomNav().findElements(By.tagName("li"));
        WebElement kontaktButton = elements.get(elements.size() - 1).findElement(By.cssSelector("a[href=\"https://qualityminds.de/kontakt/\"]"));
        kontaktButton.click();

        step5Url = kontaktPageAssertions(driver);
    }

    @Test(dependsOnMethods =
            {"assertThatQualityMindsPageIsOpened",
                    "assertThatKontaktAndAnfahrtPageIsDisplayed",
                    "assertThatContainsEmailAddress"
                    , "navigateBackToMainPage"
                    , "KontaktAndAnfahrtPageIsDisplayed"})
    public void assertThatPagesDisplayedInStep2And5AreTheSame() {
        assertEquals(step3Url, step5Url);
        Reporter.log("Pages displayed in step 2 and 5 are the same");

    }




    @AfterTest
    public void tearDown() {

        driver.quit();
    }
}
