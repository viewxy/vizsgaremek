import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class BlogPage {
    WebDriver driver;

    public BlogPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By seeAllButton = By.xpath("//*[contains(@class, \"m-3\")]"); //

    public AllBlogPostPage clickSeeAllButton() {
        driver.findElement(seeAllButton).click();
        return new AllBlogPostPage(driver);
    }


}
