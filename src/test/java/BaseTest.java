import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class BaseTest implements ITestListener {
    WebDriver driver;

    @Override
    public void onTestFailure(ITestResult result) {
        driver = new ChromeDriver();
        saveFailureScreenShot(driver);
    }

    @Attachment
    public byte[] saveFailureScreenShot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
