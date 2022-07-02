import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.*;

public class TestLoginPage extends TestPortio {

    @Test
    @Tags({@Tag("Bejelentkezés"), @Tag("Regisztráció"), @Tag("ÚjAdatBevitel")})
    @DisplayName("Sikeres Sign in helyes adatokkal")
    @Description("Bejelentkezés a regisztrációnál megadottakkal egyező adatokkal")
    @Severity(SeverityLevel.CRITICAL)
    public void loginSuccessfulTest(TestInfo testInfo) {
        basePage.clickTermsAndConditionsAccept();
        RegisterPage registerPage = basePage.clickRegister();
        registerPage.registerProcess("viewxy", "pass123", "viewxy@gmail.com", "");
        LoginPage loginPage = registerPage.clickLogin();

        LandingPage landingPage = loginPage.loginProcess("viewxy", "pass123");

        addAttachment(testInfo.getDisplayName());

        Assertions.assertTrue(landingPage.isNavBarDisplayed());
    }

    @Test
    @Tags({@Tag("Bejelentkezés"), @Tag("Regisztráció"), @Tag("ÚjAdatBevitel")})
    @DisplayName("Sikertelen Sign in helytelen username-el")
    @Description("Bejelentkezés helytelen felhasználóvévvel")
    @Severity(SeverityLevel.CRITICAL)
    public void loginWrongUserTest(TestInfo testInfo) {
        basePage.clickTermsAndConditionsAccept();
        RegisterPage registerPage = basePage.clickRegister();
        registerPage.registerProcess("viewxy", "pass123", "viewxy@gmail.com", "");
        registerPage.clickRegisterSubmitButton();

        LoginPage loginPage = registerPage.clickLogin();

        LandingPage landingPage = loginPage.loginProcess("view-xy", "pass123");

        addAttachment(testInfo.getDisplayName());

        Assertions.assertFalse(landingPage.isNavBarDisplayed());
    }

    @Test
    @Tags({@Tag("Bejelentkezés"), @Tag("Regisztráció"), @Tag("ÚjAdatBevitel")})
    @DisplayName("Sikertelen Sign in helytelen passworddel")
    @Description("Bejelentkezés helytelen jelszóval")
    @Severity(SeverityLevel.CRITICAL)
    public void loginWrongPasswordTest(TestInfo testInfo) {
        basePage.clickTermsAndConditionsAccept();
        RegisterPage registerPage = basePage.clickRegister();
        registerPage.registerProcess("viewxy", "pass123", "viewxy@gmail.com", "");

        LoginPage loginPage = registerPage.clickLogin();

        loginPage.loginProcess("viewxy", "pass1");

        addAttachment(testInfo.getDisplayName());

        Assertions.assertTrue(loginPage.isAlertMessageDisplayed());
    }

    @Test
    @Tags({@Tag("Bejelentkezés"), @Tag("Regisztráció"), @Tag("ÚjAdatBevitel")})
    @DisplayName("Sikertelen Sign in üres adatokkal")
    @Description("Bejelentkezés üres adatokkal")
    @Severity(SeverityLevel.CRITICAL)
    public void loginEmptyTest(TestInfo testInfo) {
        basePage.clickTermsAndConditionsAccept();
        RegisterPage registerPage = basePage.clickRegister();
        registerPage.registerProcess("viewxy", "pass123", "viewxy@gmail.com", "");

        LoginPage loginPage = registerPage.clickLogin();

        LandingPage landingPage = loginPage.loginProcess("", "");

        addAttachment(testInfo.getDisplayName());

        Assertions.assertFalse(landingPage.isNavBarDisplayed());
    }
}
