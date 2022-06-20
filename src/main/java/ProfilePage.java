import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {
    WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    private final By deleteAccountButton = By.xpath("//*[@onclick=\"showRealDeleteAccBtn()\"]");
    private final By iAmSureButton = By.id("delete-account-btn");

    public void clickDeleteAccountButton() {
        driver.findElement(deleteAccountButton).click();
    }

    public LoginPage clickIAmSureButton() {
        driver.findElement(iAmSureButton).click();
        return new LoginPage(driver);
    }
}
