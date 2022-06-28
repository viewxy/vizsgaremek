import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.Dimension;

public class TestLandingPage extends TestPortio {

    @Test
    @DisplayName("Logout")
    @Description("Kijelentkezés")
    @Severity(SeverityLevel.CRITICAL)
    public void logOutTest(TestInfo testInfo) {
        basePage.clickTermsAndConditionsAccept();
        RegisterPage registerPage = basePage.clickRegister();
        registerPage.registerProcess("viewxy", "pass123", "viewxy@gmail.com", "");

        LoginPage loginPage = registerPage.clickLogin();
        LandingPage landingPage = loginPage.loginProcess("viewxy", "pass123");

        landingPage.clickLogoutButton();

        boolean actual = loginPage.isLoginWindowDisplayed();

        addAttachment(testInfo.getDisplayName());

        Assertions.assertTrue(actual);
    }

    @Test
    @DisplayName("h2 Heading szövegstílus ellenőrzése")
    @Description("Az oldalon konzisztens a  h2 heading-ek szövegstílusa, betűtípusa")
    @Severity(SeverityLevel.MINOR)
    public void h2HeadingFontTest(TestInfo testInfo) {
        basePage.clickTermsAndConditionsAccept();
        RegisterPage registerPage = basePage.clickRegister();
        registerPage.registerProcess("viewxy", "pass123", "viewxy@gmail.com", "");

        LoginPage loginPage = registerPage.clickLogin();
        LandingPage landingPage = loginPage.loginProcess("viewxy", "pass123");
        String expected = "\"yeseva one\", cursive";
        String[] actualArray = landingPage.getFontStyle(landingPage.getH2Headings());
        for (String actual : actualArray) {
            addAttachment(testInfo.getDisplayName());

            Assertions.assertEquals(expected, actual);
        }
    }

    @Test
    @DisplayName("Keskeny nézet ellenőrzés")
    @Description("Keskeny nézetben a navBar hamburger menüre vált")
    @Severity(SeverityLevel.MINOR)
    public void narrowWindowTest(TestInfo testInfo) {
        basePage.clickTermsAndConditionsAccept();
        RegisterPage registerPage = basePage.clickRegister();
        registerPage.registerProcess("viewxy", "pass123", "viewxy@gmail.com", "");

        LoginPage loginPage = registerPage.clickLogin();
        LandingPage landingPage = loginPage.loginProcess("viewxy", "pass123");
        driver.manage().window().setSize(new Dimension(980, 540));
        addAttachment(testInfo.getDisplayName());
        Assertions.assertFalse(landingPage.isNavBarDisplayed());
        Assertions.assertTrue(landingPage.isNavBarHamburgerDisplayed());
    }
}
