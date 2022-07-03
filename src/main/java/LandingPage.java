import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LandingPage extends InitialPage {

    private final By navBar = By.id("navbarCollapse");
    private final By navBarHamburger = By.xpath("//*[@class=\"navbar-toggler collapsed\"]");
    private final By workButton = By.xpath("//nav//a[contains(@href,\"portfolio\")]");
    private final By blogButton = By.xpath("//nav//a[contains(@href,\"blog\")]");
    private final By logOutButton = By.id("logout-link");
    private final By profileButton = By.id("profile-btn");
    private final By h2Headings = By.xpath("//h2");

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    public By getH2Headings() {
        return h2Headings;
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

    public PortfolioPage clickWorkButton() {
        driver.findElement(workButton).click();
        return new PortfolioPage(driver);
    }

    public String[] getFontStyle(By headings) {
        List<WebElement> headingsList = driver.findElements(headings);
        String[] headingsFont = new String[headingsList.size()];
        for (int i = 0; i < headingsList.size(); i++) {
            headingsFont[i] = headingsList.get(i).getCssValue("font-family");
        }
        return headingsFont;
    }

    public boolean isNavBarHamburgerDisplayed() {
        return driver.findElement(navBarHamburger).isDisplayed();
    }
}