import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class BlogPage extends InitialPage {

    public BlogPage(WebDriver driver) {
        super(driver);
    }

    private final By seeAllButton = By.xpath("//*[contains(@class, \"m-3\")]"); //

    public AllBlogPostPage clickSeeAllButton() {
        driver.findElement(seeAllButton).click();
        return new AllBlogPostPage(driver);
    }


}
