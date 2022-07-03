import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PortfolioPage extends InitialPage {

    private final By images = By.className("portfolio-item-thumb");
    private final By titles = By.xpath("//*[@class=\"portfolio-item\"]//h3");
    public PortfolioPage(WebDriver driver) {
        super(driver);
    }

    public String[] getImages() {
        List<WebElement> imagesList = driver.findElements(images);
        String[] imageSource = new String[imagesList.size()];
        for (int i = 0; i < imagesList.size(); i++) {
            imageSource[i] = imagesList.get(i).getAttribute("src");
        }
        return imageSource;
    }

    public String[] getImagesName() {
        List<WebElement> titlesList = driver.findElements(titles);
        String[] titleNames = new String[titlesList.size()];
        for (int i = 0; i < titlesList.size(); i++) {
            titleNames[i] = titlesList.get(i).getText();
        }
        return titleNames;
    }
}
