import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PortfolioPage {
WebDriver driver;

    public PortfolioPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By work = By.xpath("//*[text()=\"Case Study One\"]");

    public WorkDetailsPage clickWork() {
        driver.findElement(work).click();
        return new WorkDetailsPage(driver);
    }
}
