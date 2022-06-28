import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ResumePage extends BasePage {

    public ResumePage(WebDriver driver) {
        super(driver);
    }

    private final By experiencesButton = By.xpath("//*[@href=\"#experience\"]");
    private final By experienceCard = By.className("resume__education_item");
    private final By experienceDate = By.xpath(".//span");

    public void clickExperiencesButton() {
        driver.findElement(experiencesButton).click();
    }

    public String[] getExperienceCardText() {
        List<WebElement> experienceCardList = driver.findElements(experienceCard);
        String[] experienceCardText = new String[experienceCardList.size()];
        for (int i = 0; i < experienceCardList.size(); i++) {
            experienceCardText[i] = experienceCardList.get(i).findElement(experienceDate).getText();
        }
        return experienceCardText;
    }
}
