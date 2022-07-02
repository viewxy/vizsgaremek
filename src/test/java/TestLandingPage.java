import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Dimension;
import org.testng.asserts.SoftAssert;

public class TestLandingPage extends TestPortio {

    @Test
    @Tag("Kijelentkezés")
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
    @Tag("AdatokListázása")
    @DisplayName("h2 Heading szövegstílus ellenőrzése")
    @Description("Az oldalon konzisztens a  h2 heading-ek szövegstílusa, betűtípusa")
    @Severity(SeverityLevel.MINOR)
    public void h2HeadingFontTest(TestInfo testInfo) {
        SoftAssert softAssert = new SoftAssert();
        basePage.clickTermsAndConditionsAccept();
        RegisterPage registerPage = basePage.clickRegister();
        registerPage.registerProcess("viewxy", "pass123", "viewxy@gmail.com", "");

        LoginPage loginPage = registerPage.clickLogin();
        LandingPage landingPage = loginPage.loginProcess("viewxy", "pass123");
        String expected = "\"yeseva one\", cursive";
        String[] actualArray = landingPage.getFontStyle(landingPage.getH2Headings());
        for (String actual : actualArray) {
            addAttachment(testInfo.getDisplayName());

            softAssert.assertEquals(expected, actual);
            softAssert.assertAll();
        }
    }

    @Test
    @Tag("Reszponzivitás")
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
