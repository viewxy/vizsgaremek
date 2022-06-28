import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

public class TestProfilePage extends TestPortio {

    @Test
    @DisplayName("Profil törlése automatikus kijelentkezés")
    @Description("Regisztrált profil törlése az adatbázisból, automatikus kijelentkezés ellenőrzése")
    @Severity(SeverityLevel.NORMAL)
    public void deleteProfileLogoutTest(TestInfo testInfo) {
        basePage.clickTermsAndConditionsAccept();
        RegisterPage registerPage = basePage.clickRegister();
        registerPage.registerProcess("viewxy", "pass123", "viewxy@gmail.com", "");

        LoginPage loginPage = registerPage.clickLogin();
        LandingPage landingPage = loginPage.loginProcess("viewxy", "pass123");

        ProfilePage profilePage = landingPage.clickProfileButton();
        profilePage.clickDeleteAccountButton();
        loginPage = profilePage.clickIAmSureButton();

        addAttachment(testInfo.getDisplayName());

        Assertions.assertTrue(loginPage.isLoginWindowDisplayed());
    }

    @Test
    @DisplayName("Profil törlése ellenőrzése")
    @Description("Regisztrált profil törlése az adatbázisból, törlés ellenőrzése")
    @Severity(SeverityLevel.NORMAL)
    public void deleteProfileTest(TestInfo testInfo) {
        basePage.clickTermsAndConditionsAccept();
        RegisterPage registerPage = basePage.clickRegister();
        registerPage.registerProcess("viewxy", "pass123", "viewxy@gmail.com", "");

        LoginPage loginPage = registerPage.clickLogin();
        LandingPage landingPage = loginPage.loginProcess("viewxy", "pass123");

        ProfilePage profilePage = landingPage.clickProfileButton();
        profilePage.clickDeleteAccountButton();
        loginPage = profilePage.clickIAmSureButton();
        loginPage.loginProcess("viewxy", "pass123");

        addAttachment(testInfo.getDisplayName());

        Assertions.assertTrue(loginPage.isLoginWindowDisplayed());
    }
}
