import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AllBlogPostPage extends BasePage {

    public AllBlogPostPage(WebDriver driver) {
        super(driver);
    }

    private final By nextButton = By.xpath("//*[@rel=\"next\"]");
    private final By lastPageButton = By.xpath("//nav[@aria-label=\"Page navigation\"]//li[last()]");
    private final By blogPostTitleContentBox = By.xpath("//div[@class=\"blog-page__item-content bg-white\"]");
    private final By blogPostMainTitle = By.xpath(".//a");

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    public boolean isNextButtonDisplayed() {
        try {
            return driver.findElement(nextButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String isLastPageButtonActive() {
        return driver.findElement(lastPageButton).getAttribute("class");
    }

    public String[] getArrayOfBlogTitles() {
        List<WebElement> blogPostTitles = driver.findElements(blogPostTitleContentBox);
        String[] names = new String[blogPostTitles.size()];

        for (int i = 0; i < blogPostTitles.size(); i++) {
            names[i] = blogPostTitles.get(i).findElement(blogPostMainTitle).getText();
        }
        return names;
    }
}

