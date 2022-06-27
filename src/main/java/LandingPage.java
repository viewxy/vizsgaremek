import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LandingPage {
    WebDriver driver;

    public LandingPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By navBar = By.id("navbarCollapse");

    private final By homeButton = By.xpath("//nav//a[contains(@href,\"home\")]");
    private final By aboutButton = By.xpath("//nav//a[contains(@href,\"about\")]");
    private final By serviceButton = By.xpath("//nav//a[contains(@href,\"service\")]");
    private final By workButton = By.xpath("//nav//a[contains(@href,\"portfolio\")]");
    private final By resumeButton = By.xpath("//nav//a[contains(@href,\"resume\")]");
    private final By skillsButton = By.xpath("//nav//a[contains(@href,\"skills\")]");
    private final By blogButton = By.xpath("//nav//a[contains(@href,\"blog\")]");
    private final By contactButton = By.xpath("//nav//a[contains(@href,\"contact\")]");


    private final By logOutButton = By.id("logout-link");
    private final By profileButton = By.id("profile-btn");

    private final By h2Headings = By.xpath("//h2");
    private final By h3Headings = By.xpath("//h3");
    private final By h4Headings = By.xpath("//h4");
    private final By h5Headings = By.xpath("//h5");
    private final By p = By.xpath("//p");

    public By getH2Headings() {
        return h2Headings;
    }

    public By getH3Headings() {
        return h3Headings;
    }

    public By getH4Headings() {
        return h4Headings;
    }

    public By getH5Headings() {
        return h5Headings;
    }

    public By getP() {
        return p;
    }

    public boolean isNavBarDisplayed() {
        try {
            return driver.findElement(navBar).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public ProfilePage clickProfileButton() {
        driver.findElement(profileButton).click();
        return new ProfilePage(driver);
    }

    public BlogPage clickBlogButton() {
        driver.findElement(blogButton).click();
        return new BlogPage(driver);
    }

    public LoginPage clickLogoutButton() {
        driver.findElement(logOutButton).click();
        return new LoginPage(driver);
    }

    public ResumePage clickResumeButton() {
        driver.findElement(resumeButton).click();
        return new ResumePage(driver);
    }

    public String[] getFontStyle(By headings) {
        List<WebElement> headingsList = driver.findElements(headings);
        String[] h2HeadingsFont = new String[headingsList.size()];
        for (int i = 0; i < headingsList.size(); i++) {
            h2HeadingsFont[i] = headingsList.get(i).getCssValue("font");
        }
        return h2HeadingsFont;
    }
}