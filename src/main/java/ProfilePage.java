import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProfilePage extends InitialPage {

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    private final By deleteAccountButton = By.xpath("//*[@onclick=\"showRealDeleteAccBtn()\"]");
    private final By iAmSureButton = By.id("delete-account-btn");
    private final By nameField = By.id("name");
    private final By bioField = By.id("bio");
    private final By phoneNumberField = By.id("phone-number");
    private final By saveProfileButton = By.xpath("//*[@onclick=\"editUser()\"]");
    private final By saveProfileMessage = By.id("edit-alert");

    public void clickDeleteAccountButton() {
        driver.findElement(deleteAccountButton).click();
    }

    public LoginPage clickIAmSureButton() {
        driver.findElement(iAmSureButton).click();
        return new LoginPage(driver);
    }

    public void fillNameField(String name) {
        WebElement nameBox = driver.findElement(nameField);
        nameBox.clear();
        nameBox.sendKeys(name);
    }

    public void fillBioField(String bio) {
        WebElement bioBox = driver.findElement(bioField);
        bioBox.clear();
        bioBox.sendKeys(bio);
    }

    public void fillPhoneNumber(String phoneNumber) {
        WebElement phoneNumberBox = driver.findElement(phoneNumberField);
        phoneNumberBox.clear();
        phoneNumberBox.sendKeys(phoneNumber);
    }

    public void clickSaveProfileButton() {
        driver.findElement(saveProfileButton).click();
    }

    public String getSaveProfileMessage() {
        return driver.findElement(saveProfileMessage).getText();
    }

}
