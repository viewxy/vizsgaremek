import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends InitialPage {

    private final By usernameFieldLogin = By.id("email");
    private final By passwordFieldLogin = By.id("password");
    private final By loginSubmitButton = By.xpath("//*[@onclick=\"myFunction()\"]");
    private final By alertMessage = By.id("alert");
    private final By loginWindow = By.id("login");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void fillUsernameFieldLogin(String usernameLoginText) {
        WebElement usernameBox = driver.findElement(usernameFieldLogin);
        usernameBox.clear();
        usernameBox.sendKeys(usernameLoginText);
    }

    public void fillPasswordFieldLogin(String passwordLoginText) {
        WebElement passwordBox = driver.findElement(passwordFieldLogin);
        passwordBox.clear();
        passwordBox.sendKeys(passwordLoginText);
    }

    public LandingPage clickLoginSubmitButton() {
        driver.findElement(loginSubmitButton).click();
        return new LandingPage(driver);
    }

    public boolean isAlertMessageDisplayed() {
        return driver.findElement(alertMessage).isDisplayed();
    }

    public boolean isLoginWindowDisplayed() {
        return driver.findElement(loginWindow).isDisplayed();
    }

    public LandingPage loginProcess(String usernameLoginText, String passwordLoginText) {
        fillUsernameFieldLogin(usernameLoginText);
        fillPasswordFieldLogin(passwordLoginText);
        return clickLoginSubmitButton();
    }
}
