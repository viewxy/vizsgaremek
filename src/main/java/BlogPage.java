import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BlogPage extends InitialPage {

    private final By seeAllButton = By.xpath("//*[contains(@class, \"m-3\")]");

    public BlogPage(WebDriver driver) {
        super(driver);
    }

    public AllBlogPostPage clickSeeAllButton() {
        driver.findElement(seeAllButton).click();
        return new AllBlogPostPage(driver);
    }
}
