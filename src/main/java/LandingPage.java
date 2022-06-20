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

    private final By portfolioButton = By.xpath("//*[@href=\"https://lennertamas.github.io/portio/#portfolio\"]");
    private final By blogButton = By.xpath("//nav//a[contains(@href,\"blog\")]");

    private final By logOutButton = By.id("logout-link");



    public boolean isNavBarDisplayed() {
        try {
            return driver.findElement(navBar).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public PortfolioPage clickPortfolioButton() {
        driver.findElement(portfolioButton).click();
        return new PortfolioPage(driver);
    }

    public BlogPage clickBlogButton() {
        driver.findElement(blogButton).click();
        return new BlogPage(driver);
    }

    public LoginPage clickLogoutButton() {
        driver.findElement(logOutButton).click();
        return new LoginPage(driver);
    }

   // String logoSRC = logo.getAttribute("src");
}
