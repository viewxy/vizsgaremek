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

    public String[] getFontStyle() {
        List<WebElement> h2HeadingsList = driver.findElements(h2Headings);
        String[] h2HeadingsFont = new String[h2HeadingsList.size()];
        for (int i = 0; i < h2HeadingsList.size(); i++) {
            h2HeadingsFont[i] = h2HeadingsList.get(i).getCssValue("font-family");
        }
        return h2HeadingsFont;
    }
}