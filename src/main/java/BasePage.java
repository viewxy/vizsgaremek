import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {
    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }


    private final String portioURL = "https://lennertamas.github.io/portio/";
    private final By termsAndConditionsAccept = By.id("terms-and-conditions-button");
    private final By termsAndConditionsExit = By.className("CloseIcon");
    private final By termsAndConditionsMainText = By.className("row");
    private final By overlay = By.id("overlay");
    private final By popUp = By.className("popup");


    private final By register = By.xpath("(//*[@id=\"register-form-button\"])[1]");


    public void navigate() {
        driver.navigate().to(portioURL);
    }

    public void clickTermsAndConditionsAccept() {
        driver.findElement(termsAndConditionsAccept).click();
    }

    public void clickTermsAndConditionsExit() {
        driver.findElement(termsAndConditionsExit).click();
    }

    public void clickOverlay() {
        driver.findElement(overlay).click();
    }

    public boolean isPopUpDisplayed() {
        return driver.findElement(popUp).isDisplayed();
    }

    public RegisterPage clickRegister() {
        driver.findElement(register).click();
        return new RegisterPage(driver);
    }

    public String getTermsAndConditionsText() {
        return driver.findElement(termsAndConditionsMainText).getText();
    }
}
