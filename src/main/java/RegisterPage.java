import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage {
    WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By usernameFieldRegister = By.id("register-username");
    private final By passwordFieldRegister = By.id("register-password");
    private final By emailFieldRegister = By.id("register-email");
    private final By description = By.id("register-description");
    private final By registerSubmitButton = By.xpath("//button[@onclick=\"registerUser()\"]");

    private final By registrationValidation = By.id("register-alert");

    private final By login = By.xpath("(//*[@id=\"login-form-button\"])[2]");

    public void fillUsernameField(String usernameText) {
        WebElement usernameField = driver.findElement(usernameFieldRegister);
        usernameField.clear();
        usernameField.sendKeys(usernameText);
    }

    public void fillPasswordField(String passwordText) {
        WebElement passwordField = driver.findElement(passwordFieldRegister);
        passwordField.clear();
        passwordField.sendKeys(passwordText);
    }

    public void fillEmailField(String emailText) {
        WebElement emailField = driver.findElement(emailFieldRegister);
        emailField.clear();
        emailField.sendKeys(emailText);
    }

    public void fillDescription(String descriptionText) {
        WebElement descBox = driver.findElement(description);
        descBox.clear();
        descBox.sendKeys(descriptionText);
    }

    public void clickRegisterSubmitButton() {
        driver.findElement(registerSubmitButton).click();
    }

    public String checkRegistrationValidation() {

        return driver.findElement(registrationValidation).getText();
    }

    public LoginPage clickLogin() {
        driver.findElement(login).click();
        return new LoginPage(driver);
    }

    public void registerProcess(String usernameText, String passwordText, String emailText, String descriptionText) {
        fillUsernameField(usernameText);
        fillPasswordField(passwordText);
        fillEmailField(emailText);
        fillDescription(descriptionText);
        clickRegisterSubmitButton();
    }
}
